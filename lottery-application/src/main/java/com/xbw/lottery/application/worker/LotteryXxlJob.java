package com.xbw.lottery.application.worker;


import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.google.gson.Gson;
import com.xbw.lottery.application.mq.producer.KafkaProducer;
import com.xbw.lottery.common.Constants;
import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.activity.model.vo.ActivityVO;
import com.xbw.lottery.domain.activity.model.vo.InvoiceVO;
import com.xbw.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.xbw.lottery.domain.activity.service.partake.IActivityPartake;
import com.xbw.lottery.domain.activity.service.stateflow.IStateHandler;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 活动扫描任务
 * <p>
 * 扫描待处理的活动列表，状态为：通过、活动中。每10条每10条一起处理
 * <p>
 * 通过 -> 时间符合时 -> 活动中
 * <p>
 * 活动中 -> 时间到期时 -> 关闭
 */

@Component
@Slf4j
public class LotteryXxlJob {

    @Resource
    private IActivityDeploy activityDeploy;


    @Resource
    private IStateHandler stateHandler;

    @Resource
    private KafkaProducer kafkaProducer;

    @Resource
    private IDBRouterStrategy dbRouter;

    @Resource
    private IActivityPartake activityPartake;

    Gson gson = new Gson();


    /**
     * 扫描更新活动状态
     *
     * @throws Exception
     */
    @XxlJob("lotteryActivityStateJobHandler")
    public void lotteryActivityStateJobHandler() throws Exception {
        log.info("扫描活动状态 begin");

        // 从第一行开始扫描
        List<ActivityVO> activityVOList = activityDeploy.scanToDoActivityList(0L);
        if (activityVOList.isEmpty()) {
            log.info("扫描活动状态 End 暂无符合需要扫描的活动列表");
            return;
        }

        while (!activityVOList.isEmpty()) {
            for (ActivityVO activityVO : activityVOList) {
                Integer state = activityVO.getState();

                // 活动状态为审核通过，在临近活动开启时间前，审核活动为活动中。在使用活动的时候，需要依照活动状态核时间两个字段进行判断和使用。
                if (Constants.ActivityState.PASS.getCode().equals(state)) {
                    Result doingResult = stateHandler.doing(activityVO.getActivityId(), Constants.ActivityState.PASS);
                    log.info("扫描活动状态为活动中 结果：{} activityId：{} activityName：{} creator：{}",
                            gson.toJson(doingResult), activityVO.getActivityId(), activityVO.getActivityName(),
                            activityVO.getCreator());

                    // 扫描时间已过期的活动，从活动中状态变更为关闭状态【这里也可以细化为2个任务来处理，也可以把时间判断放到数据库中操作】
                } else if (Constants.ActivityState.DOING.getCode().equals(state)) {
                    if (activityVO.getEndDateTime().before(new Date())) {
                        Result doingResult = stateHandler.close(activityVO.getActivityId(), Constants.ActivityState.DOING);
                        log.info("扫描活动状态为关闭 结果：{} activityId：{} activityName：{} creator：{}",
                                gson.toJson(doingResult), activityVO.getActivityId(), activityVO.getActivityName(),
                                activityVO.getCreator());
                    }
                }
            }

            // 获取该列表的最后一个记录，继续往后扫描10个
            ActivityVO activityVO = activityVOList.get(activityVOList.size() - 1);
            activityVOList = activityDeploy.scanToDoActivityList(activityVO.getActivityId());
        }

        log.info("扫描活动状态 End");
    }


    /**
     * 扫描订单表的mq发送状态，做mq补偿
     */
    @XxlJob("lotteryOrderMQStateJobHandler")
    public void lotteryOrderMQStateJobHandler() throws Exception {
        // 校验参数
        String jobParam = XxlJobHelper.getJobParam();
        if (null == jobParam) {
            log.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 错误 params is null");
            return;
        }

        // 获取分布式任务配置参数信息 参数配置格式：1,2,3 也可以是指定扫描一个，
        // 也可以配置多个库，按照部署的任务集群进行数量配置，均摊分别扫描效率更高
        String[] params = jobParam.split(",");

        if (params.length == 0) {
            log.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 结束 params is null");
            return;
        }

        log.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 开始 params：{}", gson.toJson(params));

        // 获取分库分表配置下的分表数
        int tbCount = dbRouter.tbCount();

        // 遍历指定的库
        for (String param : params) {
            int dbCount = Integer.parseInt(param);
            if (dbCount > dbRouter.dbCount()) {
                log.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 结束 db not exist");
                continue;
            }

            // 遍历该库下的各个表
            for (int i = 0; i < tbCount; i++) {
                List<InvoiceVO> invoiceVOList = activityPartake.scanInvoiceMqState(dbCount, i);
                log.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 扫描库：{} 扫描表：{} 需要补偿数：{}", dbCount, i, invoiceVOList.size());

                for (InvoiceVO invoiceVO : invoiceVOList) {
                    ListenableFuture<SendResult<String, Object>> future = kafkaProducer.sendLotteryInvoice(invoiceVO);

                    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                        @Override
                        public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                            // MQ 消息发送完成，更新数据库表 user_strategy_export.mq_state = 1
                            activityPartake.updateInvoiceMqState(invoiceVO.getuId(),
                                    invoiceVO.getOrderId(), Constants.MQState.COMPLETE.getCode());
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            // MQ 消息发送失败，更新数据库表 user_strategy_export.mq_state = 2 【等待下一次定时任务扫码补偿MQ消息】
                            activityPartake.updateInvoiceMqState(invoiceVO.getuId(),
                                    invoiceVO.getOrderId(), Constants.MQState.FAIL.getCode());
                        }

                    });
                }


            }
        }

        log.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 完成 param：{}", gson.toJson(params));
    }
}

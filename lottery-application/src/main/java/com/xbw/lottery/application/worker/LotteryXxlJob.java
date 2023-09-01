package com.xbw.lottery.application.worker;


import com.google.gson.Gson;
import com.xbw.lottery.common.Constants;
import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.activity.model.vo.ActivityVO;
import com.xbw.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.xbw.lottery.domain.activity.service.stateflow.IStateHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    Gson gson = new Gson();

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
}

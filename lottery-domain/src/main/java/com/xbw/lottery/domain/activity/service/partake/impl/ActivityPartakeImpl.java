package com.xbw.lottery.domain.activity.service.partake.impl;


import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.xbw.lottery.common.Constants;
import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.activity.model.req.PartakeReq;
import com.xbw.lottery.domain.activity.model.vo.ActivityBillVO;
import com.xbw.lottery.domain.activity.model.vo.DrawOrderVO;
import com.xbw.lottery.domain.activity.model.vo.InvoiceVO;
import com.xbw.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.xbw.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.xbw.lottery.domain.activity.service.partake.BaseActivityPartake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动参与功能实现
 */
@Service
@Slf4j
public class ActivityPartakeImpl extends BaseActivityPartake {

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private IDBRouterStrategy dbRouter;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    protected Result checkActivityBill(PartakeReq partake, ActivityBillVO bill) {

        // 校验：活动状态，需要活动执行中
        if (!Constants.ActivityState.DOING.getCode().equals(bill.getActivityState())) {
            log.warn("活动当前状态非可用 state：{}", bill.getActivityName());
            return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动当前状态非可用");
        }

        // 校验：活动日期
        if (bill.getBeginDateTime().after(partake.getPartakeDate()) || bill.getEndDateTime().before(partake.getPartakeDate())) {
            log.warn("活动时间范围非可用 beginDateTime：{} endDateTime：{}", bill.getBeginDateTime(), bill.getEndDateTime());
            return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动时间范围非可用");
        }

        // 校验：活动库存
        if (bill.getStockSurplusCount() <= 0) {
            log.warn("活动剩余库存非可用 stockSurplusCount：{}", bill.getStockSurplusCount());
            return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动剩余库存非可用");
        }

        // 校验：个人库存，即个人活动剩余可领取次数
        if (null != bill.getUserTakeLeftCount() && bill.getUserTakeLeftCount() <= 0) {
            log.warn("个人领取次数非可用 userTakeLeftCount：{}", bill.getUserTakeLeftCount());
            return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "个人领取次数非可用");
        }

        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        int updateCount = activityRepository.subtractionActivityStock(req.getActivityId());
        if (0 == updateCount) {
            log.error("扣减活动库存失败 activityId：{}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.UPDATE_FAIL);
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result grabActivity(PartakeReq partake, ActivityBillVO bill, Long takeId) {

        try {
            dbRouter.doRouter(partake.getuId());

            // 编程式事务
            return transactionTemplate.execute(status -> {
                try {
                    // 扣减个人剩余可参与次数
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId(),
                            bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(),
                            partake.getuId());
                    if (0 == updateCount) {
                        // 回滚
                        status.setRollbackOnly();
                        log.error("领取活动，扣减个人剩余可参与次数失败 activityId：{} uId：{}", partake.getActivityId(), partake.getuId());
                        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR);
                    }

                    // 插入领取活动信息
                    userTakeActivityRepository.takeActivity(bill.getActivityId(), bill.getActivityName(),
                            bill.getStrategyId(), bill.getTakeCount(), bill.getUserTakeLeftCount(),
                            partake.getuId(), partake.getPartakeDate(), takeId);

                } catch (DuplicateKeyException e) {
                    // 插入领取活动信息失败，唯一索引冲突
                    status.setRollbackOnly();
                    log.error("领取活动失败，唯一索引冲突 activityId：{} uId：{}", partake.getActivityId(), partake.getuId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrder) {
        try {
            dbRouter.doRouter(drawOrder.getuId());
            return transactionTemplate.execute(status -> {
                try {
                    // 锁定活动领取记录（set use_state = 1）
                    int lockCount = userTakeActivityRepository.lockTackActivity(drawOrder.getuId(), drawOrder.getActivityId(), drawOrder.getTakeId());
                    if (0 == lockCount) {
                        status.setRollbackOnly();
                        log.error("记录中奖单，个人参与活动抽奖已消耗完 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getuId());
                        return Result.buildResult(Constants.ResponseCode.UPDATE_FAIL);
                    }

                    // 保存抽奖记录
                    userTakeActivityRepository.saveUserStrategyExport(drawOrder);

                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    log.error("记录中奖单失败，唯一索引冲突 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getuId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return null;
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public void updateInvoiceMqState(String uId, Long orderId, Integer mqState) {
        userTakeActivityRepository.updateInvoiceMqState(uId, orderId, mqState);
    }

    @Override
    public List<InvoiceVO> scanInvoiceMqState(int dbCount, int tbCount) {
       try {
           // 设置路由
           dbRouter.setDBKey(dbCount);
           dbRouter.setTBKey(tbCount);

           // 查询数据
           return userTakeActivityRepository.scanInvoiceMqState();
       } finally {
           dbRouter.clear();
       }
    }
}

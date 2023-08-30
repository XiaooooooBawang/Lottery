package com.xbw.lottery.domain.activity.service.stateflow.event;


import com.xbw.lottery.common.Constants;
import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * 提审状态
 * 在此状态下触发转移状态操作，可执行转移状态操作即可改变状态，不可执行则返回错误提示
 */
@Component
public class ArraignmentState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildErrorResult("待审核状态不可重复提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        boolean changeSuccess = activityRepository.alterState(activityId, currentStatus, Constants.ActivityState.PASS);
        return changeSuccess ? Result.buildSuccessResult("活动审核通过") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        boolean changeSuccess = activityRepository.alterState(activityId, currentStatus, Constants.ActivityState.REFUSE);
        return changeSuccess ? Result.buildSuccessResult("活动审核拒绝") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        boolean changeSuccess = activityRepository.alterState(activityId, currentStatus, Constants.ActivityState.EDIT);
        return changeSuccess ? Result.buildSuccessResult("活动审核撤销回到编辑中") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        boolean changeSuccess = activityRepository.alterState(activityId, currentStatus, Constants.ActivityState.CLOSE);
        return changeSuccess ? Result.buildSuccessResult("活动审核关闭") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildErrorResult("非关闭活动不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildErrorResult("待审核活动不可变更为活动中");
    }
}

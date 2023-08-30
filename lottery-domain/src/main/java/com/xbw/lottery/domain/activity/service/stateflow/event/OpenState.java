package com.xbw.lottery.domain.activity.service.stateflow.event;


import com.xbw.lottery.common.Constants;
import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * 活动开启状态
 */
@Component
public class OpenState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动开启不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动开启不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动开启不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动开启不可撤销审核");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterState(activityId, currentState, Constants.ActivityState.CLOSE);
        return isSuccess ? Result.buildSuccessResult("活动关闭完成") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动不可重复开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterState(activityId, currentState, Constants.ActivityState.DOING);
        return isSuccess ? Result.buildSuccessResult("活动变更活动中完成") : Result.buildErrorResult("活动状态变更失败");
    }

}

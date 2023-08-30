package com.xbw.lottery.domain.activity.service.stateflow;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * 活动状态抽象类
 * 在整个接口中提供了各项状态流转服务的接口，例如；活动提审、审核通过、审核拒绝、撤审撤销等7个方法。
 * 在这些方法中所有的入参都是一样的，activityId(活动ID)、currentStatus(当前状态)，只有他们的具体实现是不同的。
 */
public abstract class AbstractState {

    @Resource
    protected IActivityRepository activityRepository;

    /**
     * 提审
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    public abstract Result arraignment(Long activityId, Enum<Constants.ActivityState> currentStatus);


    /**
     * 审核通过
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    public abstract Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus);


    /**
     * 审核拒绝
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    public abstract Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus);


    /**
     * 撤销审核
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    public abstract Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus);


    /**
     * 关闭
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    public abstract Result close(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     * 开启
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    public abstract Result open(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     * 运行活动中
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    public abstract Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus);
}

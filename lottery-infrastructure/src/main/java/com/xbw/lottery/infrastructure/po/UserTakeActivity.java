package com.xbw.lottery.infrastructure.po;


import java.io.Serializable;
import java.util.Date;

/**
 * 用户参与活动记录表
 * @TableName user_take_activity
 */
public class UserTakeActivity implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 活动领取ID
     */
    private Long takeId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动领取时间
     */
    private Date takeDate;

    /**
     * 抽奖策略ID
     */
    private Long strategyId;

    /**
     * 活动单使用状态 0未使用、1已使用
     */
    private Integer useState;

    /**
     * 领取次数
     */
    private Integer takeCount;

    /**
     * 防重ID
     */
    private String uuid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 自增ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 自增ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     */
    public String getuId() {
        return uId;
    }

    /**
     * 用户ID
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * 活动领取ID
     */
    public Long getTakeId() {
        return takeId;
    }

    /**
     * 活动领取ID
     */
    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    /**
     * 活动ID
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * 活动ID
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * 活动名称
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 活动名称
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * 活动领取时间
     */
    public Date getTakeDate() {
        return takeDate;
    }

    /**
     * 活动领取时间
     */
    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    /**
     * 领取次数
     */
    public Integer getTakeCount() {
        return takeCount;
    }

    /**
     * 领取次数
     */
    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    /**
     * 防重ID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 防重ID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }
}
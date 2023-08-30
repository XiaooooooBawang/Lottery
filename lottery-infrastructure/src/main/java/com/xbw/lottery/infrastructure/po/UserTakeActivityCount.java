package com.xbw.lottery.infrastructure.po;


import java.io.Serializable;
import java.util.Date;

/**
 * 用户活动参与次数表
 * @TableName user_take_activity_count
 */
public class UserTakeActivityCount implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 可领取次数
     */
    private Integer totalCount;

    /**
     * 已领取次数
     */
    private Integer leftCount;

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
     * 可领取次数
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 可领取次数
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 已领取次数
     */
    public Integer getLeftCount() {
        return leftCount;
    }

    /**
     * 已领取次数
     */
    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
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
}
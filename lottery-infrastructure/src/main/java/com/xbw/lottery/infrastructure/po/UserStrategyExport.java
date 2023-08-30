package com.xbw.lottery.infrastructure.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户策略计算结果表
 * @TableName user_strategy_export
 */
public class UserStrategyExport implements Serializable {
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
     * 订单ID
     */
    private Long orderId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 策略方式（1:单项概率、2:总体概率）
     */
    private Integer strategyMode;

    /**
     * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
     */
    private Integer grantType;

    /**
     * 发奖时间
     */
    private Date grantDate;

    /**
     * 发奖状态
     */
    private Integer grantState;

    /**
     * 发奖ID
     */
    private Long awardId;

    /**
     * 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     */
    private Integer awardType;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品内容「文字描述、Key、码」
     */
    private String awardContent;

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
     * 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 策略ID
     */
    public Long getStrategyId() {
        return strategyId;
    }

    /**
     * 策略ID
     */
    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    /**
     * 策略方式（1:单项概率、2:总体概率）
     */
    public Integer getStrategyMode() {
        return strategyMode;
    }

    /**
     * 策略方式（1:单项概率、2:总体概率）
     */
    public void setStrategyMode(Integer strategyMode) {
        this.strategyMode = strategyMode;
    }

    /**
     * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
     */
    public Integer getGrantType() {
        return grantType;
    }

    /**
     * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
     */
    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }

    /**
     * 发奖时间
     */
    public Date getGrantDate() {
        return grantDate;
    }

    /**
     * 发奖时间
     */
    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    /**
     * 发奖状态
     */
    public Integer getGrantState() {
        return grantState;
    }

    /**
     * 发奖状态
     */
    public void setGrantState(Integer grantState) {
        this.grantState = grantState;
    }

    /**
     * 发奖ID
     */
    public Long getAwardId() {
        return awardId;
    }

    /**
     * 发奖ID
     */
    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    /**
     * 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     */
    public Integer getAwardType() {
        return awardType;
    }

    /**
     * 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     */
    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    /**
     * 奖品名称
     */
    public String getAwardName() {
        return awardName;
    }

    /**
     * 奖品名称
     */
    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    /**
     * 奖品内容「文字描述、Key、码」
     */
    public String getAwardContent() {
        return awardContent;
    }

    /**
     * 奖品内容「文字描述、Key、码」
     */
    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
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
}
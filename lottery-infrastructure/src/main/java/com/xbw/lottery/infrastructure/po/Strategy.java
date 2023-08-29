package com.xbw.lottery.infrastructure.po;


import java.io.Serializable;
import java.util.Date;

/**
 * 策略配置
 * @TableName strategy
 */

public class Strategy implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 策略描述
     */
    private String strategyDesc;

    /**
     * 策略方式「1:单项概率、2:总体概率」
     */
    private Integer strategyMode;

    /**
     * 发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」
     */
    private Integer grantType;

    /**
     * 发放奖品时间
     */
    private Date grantDate;

    /**
     * 扩展信息
     */
    private String extInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
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
     * 策略描述
     */
    public String getStrategyDesc() {
        return strategyDesc;
    }

    /**
     * 策略描述
     */
    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    /**
     * 策略方式「1:单项概率、2:总体概率」
     */
    public Integer getStrategyMode() {
        return strategyMode;
    }

    /**
     * 策略方式「1:单项概率、2:总体概率」
     */
    public void setStrategyMode(Integer strategyMode) {
        this.strategyMode = strategyMode;
    }

    /**
     * 发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」
     */
    public Integer getGrantType() {
        return grantType;
    }

    /**
     * 发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」
     */
    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }

    /**
     * 发放奖品时间
     */
    public Date getGrantDate() {
        return grantDate;
    }

    /**
     * 发放奖品时间
     */
    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    /**
     * 扩展信息
     */
    public String getExtInfo() {
        return extInfo;
    }

    /**
     * 扩展信息
     */
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
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
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
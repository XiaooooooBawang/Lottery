package com.xbw.lottery.infrastructure.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 策略明细
 * @TableName strategy_detail
 */
public class StrategyDetail implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 奖品ID
     */
    private Long awardId;

    /**
     * 奖品数量
     */
    private Integer awardCount;

    /**
     * 中奖概率
     */
    private BigDecimal awardRate;

    /**
     * 奖品剩余库存
     */
    private Integer awardSurplusCount;

    /**
     * 奖品描述
     */
    private String awardDesc;

    public Integer getAwardSurplusCount() {
        return awardSurplusCount;
    }

    public void setAwardSurplusCount(Integer awardSurplusCount) {
        this.awardSurplusCount = awardSurplusCount;
    }

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }

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
     * 奖品ID
     */
    public Long getAwardId() {
        return awardId;
    }

    /**
     * 奖品ID
     */
    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    /**
     * 奖品数量
     */
    public Integer getAwardCount() {
        return awardCount;
    }

    /**
     * 奖品数量
     */
    public void setAwardCount(Integer awardCount) {
        this.awardCount = awardCount;
    }

    /**
     * 中奖概率
     */
    public BigDecimal getAwardRate() {
        return awardRate;
    }

    /**
     * 中奖概率
     */
    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
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
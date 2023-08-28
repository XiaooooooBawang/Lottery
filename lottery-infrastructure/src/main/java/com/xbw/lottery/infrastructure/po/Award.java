package com.xbw.lottery.infrastructure.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖品配置
 * @TableName award
 */
public class Award implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 奖品ID
     */
    private Long awardId;

    /**
     * 奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）
     */
    private Integer awardType;

    /**
     * 奖品数量
     */
    private Integer awardCount;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品内容「文字描述、Key、码」
     */
    private String awardContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * updateTime
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
     * 奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）
     */
    public Integer getAwardType() {
        return awardType;
    }

    /**
     * 奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）
     */
    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
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
     * updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
package com.xbw.lottery.domain.strategy.model.vo;

import java.math.BigDecimal;

/**
 * 奖品抽奖概率信息，包括奖品id、概率
 *
 */
public class AwardRateVO {
    private Long awardId;

    private BigDecimal awardRate;

    public AwardRateVO() {
    }

    public AwardRateVO(Long awardId, BigDecimal awardRate) {
        this.awardId = awardId;
        this.awardRate = awardRate;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public BigDecimal getAwardRate() {
        return awardRate;
    }

    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
    }
}

package com.xbw.lottery.domain.strategy.model.vo;

public class DrawAwardInfo {

    // 奖品ID
    private Long awardId;

    // 奖品名称
    private String awardName;

    public DrawAwardInfo() {
    }

    public DrawAwardInfo(Long awardId, String awardName) {
        this.awardId = awardId;
        this.awardName = awardName;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
}

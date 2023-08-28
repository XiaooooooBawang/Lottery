package com.xbw.lottery.domain.strategy.model.res;

/**
 * 用户执行抽奖返回类
 */
public class DrawResult {

    // 用户ID
    private String userId;

    // 策略ID
    private Long strategyId;

    // 奖品ID
    private Long awardId;

    // 奖品名称
    private String awardName;

    public DrawResult() {
    }

    public DrawResult(String userId, Long strategyId, Long awardId, String awardName) {
        this.userId = userId;
        this.strategyId = strategyId;
        this.awardId = awardId;
        this.awardName = awardName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
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

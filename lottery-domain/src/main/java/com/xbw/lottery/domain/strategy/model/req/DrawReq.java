package com.xbw.lottery.domain.strategy.model.req;


/**
 * 用户执行抽奖请求类
 */
public class DrawReq {

    // 用户Id
    private String userId;

    // 策略Id
    private Long strategyId;

    public DrawReq() {
    }

    public DrawReq(String userId, Long strategyId) {
        this.userId = userId;
        this.strategyId = strategyId;
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
}

package com.xbw.lottery.domain.strategy.model.res;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.strategy.model.vo.DrawAwardInfo;

/**
 * 用户执行抽奖返回类
 */
public class DrawResult {

    // 用户ID
    private String userId;

    // 策略ID
    private Long strategyId;

    /**
     * 中奖状态：0未中奖、1已中奖、2兜底奖 Constants.DrawState
     */
    private Integer drawState = Constants.DrawState.FAIL.getCode();

    /**
     * 中奖奖品信息
     */
    private DrawAwardInfo drawAwardInfo;

    public DrawResult() {
    }

    public DrawResult(String userId, Long strategyId, Integer drawState) {
        this.userId = userId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }

    public DrawResult(String userId, Long strategyId, Integer drawState, DrawAwardInfo drawAwardInfo) {
        this.userId = userId;
        this.strategyId = strategyId;
        this.drawState = drawState;
        this.drawAwardInfo = drawAwardInfo;
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

    public Integer getDrawState() {
        return drawState;
    }

    public void setDrawState(Integer drawState) {
        this.drawState = drawState;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }
}

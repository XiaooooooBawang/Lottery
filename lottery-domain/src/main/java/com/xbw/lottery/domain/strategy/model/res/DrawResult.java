package com.xbw.lottery.domain.strategy.model.res;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.strategy.model.vo.DrawAwardVO;

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
    private DrawAwardVO drawAwardVO;

    public DrawResult() {
    }

    public DrawResult(String userId, Long strategyId, Integer drawState) {
        this.userId = userId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }

    public DrawResult(String userId, Long strategyId, Integer drawState, DrawAwardVO drawAwardVO) {
        this.userId = userId;
        this.strategyId = strategyId;
        this.drawState = drawState;
        this.drawAwardVO = drawAwardVO;
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

    public DrawAwardVO getDrawAwardInfo() {
        return drawAwardVO;
    }

    public void setDrawAwardInfo(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }
}

package com.xbw.lottery.application.process.res;

import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * 活动抽奖结果
 */
public class DrawProcessResult extends Result {

    private DrawAwardVO drawAwardVO;

    public DrawProcessResult(Integer code, String info) {
        super(code, info);
    }

    public DrawProcessResult(Integer code, String info, DrawAwardVO drawAwardVO) {
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawAwardVO getDrawAwardVO() {
        return drawAwardVO;
    }

    public void setDrawAwardVO(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }
}

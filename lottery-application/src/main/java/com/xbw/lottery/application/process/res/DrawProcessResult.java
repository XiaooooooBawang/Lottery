package com.xbw.lottery.application.process.res;

import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.strategy.model.vo.DrawAwardInfo;

/**
 * 活动抽奖结果
 */
public class DrawProcessResult extends Result {

    private DrawAwardInfo drawAwardInfo;

    public DrawProcessResult(Integer code, String info) {
        super(code, info);
    }

    public DrawProcessResult(Integer code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }
}

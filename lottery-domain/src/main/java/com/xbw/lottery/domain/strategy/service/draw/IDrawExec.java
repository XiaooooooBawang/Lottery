package com.xbw.lottery.domain.strategy.service.draw;

import com.xbw.lottery.domain.strategy.model.req.DrawReq;
import com.xbw.lottery.domain.strategy.model.res.DrawResult;

/**
 * 用户执行抽奖的接口
 */
public interface IDrawExec {
    DrawResult doDrawExec(DrawReq drawReq);
}

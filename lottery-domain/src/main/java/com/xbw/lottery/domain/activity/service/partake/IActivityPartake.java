package com.xbw.lottery.domain.activity.service.partake;

import com.xbw.lottery.common.Result;
import com.xbw.lottery.domain.activity.model.req.PartakeReq;
import com.xbw.lottery.domain.activity.model.res.PartakeResult;
import com.xbw.lottery.domain.activity.model.vo.DrawOrderVO;

/**
 * 抽奖活动参与接口
 */
public interface IActivityPartake {

    /**
     * 参与活动
     * @param req 入参
     * @return    领取结果
     */
    PartakeResult doPartake(PartakeReq req);


    /**
     * 保存奖品单（落库）
     *
     * @param drawOrder 奖品单
     * @return          保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);



}

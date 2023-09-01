package com.xbw.lottery.domain.award.service.goods.impl;


import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.award.model.req.GoodsReq;
import com.xbw.lottery.domain.award.model.res.DistributionRes;
import com.xbw.lottery.domain.award.service.goods.DistributionBase;
import com.xbw.lottery.domain.award.service.goods.IDistributionGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 描述类商品，以文字形式展示给用户
 */
@Component
@Slf4j
public class DescGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq goodsReq) {
        String uid = goodsReq.getuId();
        Long orderId = goodsReq.getOrderId();
        Long awardId = goodsReq.getAwardId();

        log.info("返回文字中奖信息 userId：{}", uid);

        // 更新用户领奖结果
        super.updateUserAwardGrantState(uid, orderId, awardId, Constants.GrantState.COMPLETE.getCode());

        // 返回发奖结果
        return new DistributionRes(uid, Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

}

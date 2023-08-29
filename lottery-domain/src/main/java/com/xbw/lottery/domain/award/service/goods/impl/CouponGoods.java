package com.xbw.lottery.domain.award.service.goods.impl;


import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.award.model.req.GoodsReq;
import com.xbw.lottery.domain.award.model.res.DistributionRes;
import com.xbw.lottery.domain.award.service.goods.DistributionBase;
import com.xbw.lottery.domain.award.service.goods.IDistributionGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 优惠券商品
 */
@Component
@Slf4j
public class CouponGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq goodsReq) {
        String uid = goodsReq.getuId();
        String orderId = goodsReq.getOrderId();
        String awardId = goodsReq.getAwardId();

        log.info("模拟调用优惠券发放接口 userId：{} awardContent：{}", uid, goodsReq.getAwardContent());

        Integer code = Constants.DistributionState.SUCCESS.getCode();
        String info = Constants.DistributionState.SUCCESS.getInfo();

        // 更新用户领奖结果
        super.updateUserAwardDistributionState(uid, orderId, awardId, code, info);

        // 返回发奖结果
        return new DistributionRes(uid, code, info);
    }

    public Integer getAwardTypeCode() {
        return Constants.AwardType.COUPON.getCode();
    }

}
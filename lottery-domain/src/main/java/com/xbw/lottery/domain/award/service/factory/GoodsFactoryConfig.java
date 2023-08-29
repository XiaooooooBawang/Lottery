package com.xbw.lottery.domain.award.service.factory;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.award.service.goods.IDistributionGoods;
import com.xbw.lottery.domain.award.service.goods.impl.CouponGoods;
import com.xbw.lottery.domain.award.service.goods.impl.DescGoods;
import com.xbw.lottery.domain.award.service.goods.impl.PhysicalGoods;
import com.xbw.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂配置类
 * 把四种奖品的发奖，放到一个统一的配置文件类 Map 中，便于通过 AwardType 获取相应的对象，减少 if...else 的使用
 */
public class GoodsFactoryConfig {

    @Resource
    private DescGoods descGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;


    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        goodsMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsMap.put(Constants.AwardType.PHYSICAL.getCode(), physicalGoods);
        goodsMap.put(Constants.AwardType.REDEEM_CODE.getCode(), redeemCodeGoods);
        goodsMap.put(Constants.AwardType.COUPON.getCode(), couponGoods);
    }

}

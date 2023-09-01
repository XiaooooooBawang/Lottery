package com.xbw.lottery.domain.award.service.factory;

import com.xbw.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * 配送奖品的简单工厂，提供获取配送服务
 */
@Service
public class DistributionGoodsFactory extends GoodsFactoryConfig{
    public IDistributionGoods getDistributionGoodsService(Integer awardType) {
        return goodsMap.get(awardType);
    }
}

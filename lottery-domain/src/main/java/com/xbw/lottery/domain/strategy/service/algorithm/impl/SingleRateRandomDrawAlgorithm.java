package com.xbw.lottery.domain.strategy.service.algorithm.impl;

import com.xbw.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**
 * 【推荐的策略】单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖，在并发较高的情况下，能达到优化接口响应时间的目的。
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public Long randomDraw(Long strategyId, List<Long> excludeAwardIds) {
        Long[] rateTuple = rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机生成下标
        int randomVal = this.getRandomVal(100);
        int index = super.hashIndex(randomVal);

        // 根据下标找到奖品id
        Long awardId = rateTuple[index];
        // 如果抽到了抽完的奖品，则未中奖
        if (excludeAwardIds.contains(awardId)) {
            return -1L;
        }

        return awardId;
    }
}

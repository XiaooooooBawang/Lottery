package com.xbw.lottery.domain.strategy.service.algorithm;

import com.xbw.lottery.domain.strategy.model.vo.AwardRateInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 斐波那契散列算法的共用算法部分，
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm {

    // 斐波那契散列增量 = 0.6180339887
    // 2^32 * 0.6180339887 = 0x61c88647
    private final int HASH_INCREMENT = 0x61c88647;

    // 抽奖概率数组初始化长度=128，因为抽奖概率是以100%来算，所以只需128即可包含100
    private final int RATE_TUPLE_LENGTH = 128;

    // 存放概率与奖品对应的散列结果，strategyId -> rateTuple
    protected Map<Long, Long[]> rateTupleMap = new ConcurrentHashMap<>();

    // 奖品区间概率值，strategyId -> [awardId->begin、awardId->end]
    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();

    /**
     * 初始化概率数组，往里面填方奖品id
     * @param strategyId 抽奖策略id
     * @param awardRateInfoList 奖品概率配置信息列表
     */
    @Override
    public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList) {

        /*
        检查 strategyId 是否在 rateTupleMap 中存在，如果不存在，
        则创建一个长度为 RATE_TUPLE_LENGTH 的新字符串数组，并将其与 strategyId 关联。
        无论 strategyId 是否存在，最终都会返回与之关联的字符串数组。
         */
        Long[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new Long[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for (AwardRateInfo awardRateInfo : awardRateInfoList) {
            int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();

            // 往概率数组中填充奖品id
            for (int i = cursorVal + 1; i <= (cursorVal + rateVal); i++) {
                rateTuple[hashIndex(i)] = awardRateInfo.getAwardId();
            }

            cursorVal += rateVal;
        }
    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }

    /**
     * 斐波那契（Fibonacci）散列法，计算哈希索引下标值
     * @param val 概率值
     * @return 数组下标
     */
    protected int hashIndex(int val) {
        int hashcode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashcode & (RATE_TUPLE_LENGTH - 1);
    }
}

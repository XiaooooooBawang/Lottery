package com.xbw.lottery.domain.strategy.service.algorithm.impl;

import com.xbw.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.xbw.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


/**
 * 必中奖策略抽奖，排掉已经中奖的概率，重新计算中奖范围。
 */
@Component("defaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public Long randomDraw(Long strategyId, List<Long> excludeAwardIds) {
        // 初始化为零，存储有效奖品的概率之和
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        //用于存储有效（在抽奖范围内且不在排除列表中）的奖品信息
        List<AwardRateInfo> differenceAwardRateList = new ArrayList<>();
        // 遍历 awardRateInfoMap 中的奖品概率信息，将有效的奖品信息（不在排除列表中）添加到 differenceAwardRateList 中，
        // 并将对应的概率累加到 differenceDenominator。
        List<AwardRateInfo> awardRateIntervalValList = awardRateInfoMap.get(strategyId);
        for (AwardRateInfo awardRateInfo : awardRateIntervalValList) {
            if (excludeAwardIds.contains(awardRateInfo.getAwardId())) {
                continue;
            }
            differenceAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        // 前置判断，列表为空，则奖品抽完了
        if (differenceAwardRateList.size() == 0) {
            return -1L;
        }
        // 前置判断，列表只剩一个奖品，直接返回这个奖品id
        if (differenceAwardRateList.size() == 1) {
            return differenceAwardRateList.get(0).getAwardId();
        }

        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;

        Long awardId = -1L;
        int cursorVal = 0;
        for (AwardRateInfo awardRateInfo : differenceAwardRateList) {
            // 奖品概率除以剩余的总概率，保留2位小数，四舍五入，乘以100
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP)
                    .multiply((new BigDecimal(100))).intValue();

            // 随机数落在了当前奖品的范围内，返回对应奖品的ID
            if (randomVal <= cursorVal + rateVal) {
                awardId = awardRateInfo.getAwardId();
                break;
            }

            cursorVal += rateVal;
        }

        return awardId;
    }
}

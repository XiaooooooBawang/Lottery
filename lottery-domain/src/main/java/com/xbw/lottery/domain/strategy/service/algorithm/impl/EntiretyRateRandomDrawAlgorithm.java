package com.xbw.lottery.domain.strategy.service.algorithm.impl;

import com.xbw.lottery.domain.strategy.model.vo.AwardRateVO;
import com.xbw.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 必中奖策略抽奖，排掉已经中奖的概率，重新计算中奖范围。
 */
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public Long randomDraw(Long strategyId, List<Long> excludeAwardIds) {
        // 初始化为零，存储有效奖品的概率之和
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        //用于存储有效（在抽奖范围内且不在排除列表中）的奖品信息
        List<AwardRateVO> differenceAwardRateList = new ArrayList<>();
        // 遍历 awardRateInfoMap 中的奖品概率信息，将有效的奖品信息（不在排除列表中）添加到 differenceAwardRateList 中，
        // 并将对应的概率累加到 differenceDenominator。
        List<AwardRateVO> awardRateIntervalValList = awardRateInfoMap.get(strategyId);
        for (AwardRateVO awardRateVO : awardRateIntervalValList) {
            if (excludeAwardIds.contains(awardRateVO.getAwardId())) {
                continue;
            }
            differenceAwardRateList.add(awardRateVO);
            differenceDenominator = differenceDenominator.add(awardRateVO.getAwardRate());
        }

        // 前置判断，列表为空，则奖品抽完了
        if (differenceAwardRateList.size() == 0) {
            return -1L;
        }
        // 前置判断，列表只剩一个奖品，直接返回这个奖品id
        if (differenceAwardRateList.size() == 1) {
            return differenceAwardRateList.get(0).getAwardId();
        }

        int randomVal = this.getRandomVal(100);

        Long awardId = -1L;
        int cursorVal = 0;
        for (AwardRateVO awardRateVO : differenceAwardRateList) {
            // 奖品概率除以剩余的总概率，保留2位小数，四舍五入，乘以100
            int rateVal = awardRateVO.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP)
                    .multiply((new BigDecimal(100))).intValue();

            // 随机数落在了当前奖品的范围内，返回对应奖品的ID
            if (randomVal <= cursorVal + rateVal) {
                awardId = awardRateVO.getAwardId();
                break;
            }

            cursorVal += rateVal;
        }

        return awardId;
    }


}

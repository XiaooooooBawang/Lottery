package com.xbw.lottery.domain.strategy.service.draw;

import com.xbw.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.xbw.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.xbw.lottery.infrastructure.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供用于执行抽奖的公共功能
 */
public class DrawBase extends DrawConfig{
    // 验证和初始化特定策略模式的概率数组
    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {
        // 只有策略1总概率模式才需要每次抽奖都初始化概率数组
        if (1 != strategyMode) {
            return;
        }

        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        // 不需重复初始化
        if (drawAlgorithm.isExistRateTuple(strategyId)) {
            return;
        }

        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetail strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);

    }

}

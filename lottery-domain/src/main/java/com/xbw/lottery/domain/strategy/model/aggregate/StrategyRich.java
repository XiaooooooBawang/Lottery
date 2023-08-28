package com.xbw.lottery.domain.strategy.model.aggregate;

import com.xbw.lottery.infrastructure.po.Strategy;
import com.xbw.lottery.infrastructure.po.StrategyDetail;

import java.util.List;

/**
 * 策略的整个汇总信息，包括策略ID、策略配置、策略明细列表
 */
public class StrategyRich {
    // 策略ID
    private Long strategyId;

    // 策略配置
    private Strategy strategy;

    // 策略明细列表
    private List<StrategyDetail> strategyDetailList;

    public StrategyRich() {
    }

    public StrategyRich(Long strategyId, Strategy strategy, List<StrategyDetail> strategyDetailList) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetail> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetail> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }
}

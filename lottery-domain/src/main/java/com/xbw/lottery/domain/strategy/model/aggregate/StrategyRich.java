package com.xbw.lottery.domain.strategy.model.aggregate;

import com.xbw.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.xbw.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;


import java.util.List;

/**
 * 策略的整个汇总信息，包括策略ID、策略配置、策略明细列表
 */
public class StrategyRich {
    // 策略ID
    private Long strategyId;

    // 策略配置
    private StrategyBriefVO strategy;

    // 策略明细列表
    private List<StrategyDetailBriefVO> strategyDetailList;

    public StrategyRich() {
    }

    public StrategyRich(Long strategyId, StrategyBriefVO strategy, List<StrategyDetailBriefVO> strategyDetailList) {
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

    public StrategyBriefVO getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyBriefVO strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetailBriefVO> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }
}

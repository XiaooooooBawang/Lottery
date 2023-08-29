package com.xbw.lottery.domain.strategy.repository;

import com.xbw.lottery.domain.strategy.model.aggregate.StrategyRich;
import com.xbw.lottery.infrastructure.po.Award;

import java.util.List;

/**
 * Strategy相关的数据库查询接口
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(Long awardId);

    List<Long> queryNoStockStrategyAwardIdList(Long strategyId);

    boolean deductStock(Long strategyId, Long awardId);
}

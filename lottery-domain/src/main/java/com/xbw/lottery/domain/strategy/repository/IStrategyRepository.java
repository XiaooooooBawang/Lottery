package com.xbw.lottery.domain.strategy.repository;

import com.xbw.lottery.domain.strategy.model.aggregate.StrategyRich;
import com.xbw.lottery.domain.strategy.model.vo.AwardBriefVO;

import java.util.List;

/**
 * Strategy相关的数据库查询接口
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    AwardBriefVO queryAwardInfo(Long awardId);

    List<Long> queryNoStockStrategyAwardIdList(Long strategyId);

    boolean deductStock(Long strategyId, Long awardId);
}

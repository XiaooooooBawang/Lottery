package com.xbw.lottery.domain.strategy.service.draw;

import com.xbw.lottery.domain.strategy.model.aggregate.StrategyRich;
import com.xbw.lottery.domain.strategy.repository.impl.StrategyRepository;
import com.xbw.lottery.infrastructure.po.Award;

import javax.annotation.Resource;

/**
 * 抽奖策略数据支持类，一些通用的数据服务
 * 便于查询策略配置、奖品信息。通过这样的方式隔离职责。
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected StrategyRepository strategyRepository;

    /**
     * 查询策略配置信息
     *
     * @param strategyId 策略ID
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId) {
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详情信息
     *
     * @param awardId 奖品ID
     * @return 中奖详情
     */
    protected Award queryAwardInfoByAwardId(Long awardId) {
        return strategyRepository.queryAwardInfo(awardId);
    }
}

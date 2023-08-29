package com.xbw.lottery.domain.strategy.repository.impl;

import com.xbw.lottery.domain.strategy.model.aggregate.StrategyRich;
import com.xbw.lottery.domain.strategy.repository.IStrategyRepository;
import com.xbw.lottery.infrastructure.dao.IAwardDao;
import com.xbw.lottery.infrastructure.dao.IStrategyDao;
import com.xbw.lottery.infrastructure.dao.IStrategyDetailDao;
import com.xbw.lottery.infrastructure.po.Award;
import com.xbw.lottery.infrastructure.po.Strategy;
import com.xbw.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;


    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);

        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(Long awardId) {
        return awardDao.queryAward(awardId);
    }

    @Override
    public List<Long> queryNoStockStrategyAwardIdList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardIdList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, Long awardId) {
        int cnt = strategyDetailDao.deductStock(strategyId, awardId);
        return cnt == 1;
    }
}

package com.xbw.lottery.infrastructure.repository;

import com.xbw.lottery.domain.strategy.model.aggregate.StrategyRich;
import com.xbw.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.xbw.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.xbw.lottery.domain.strategy.repository.IStrategyRepository;
import com.xbw.lottery.infrastructure.dao.IAwardDao;
import com.xbw.lottery.infrastructure.dao.IStrategyDao;
import com.xbw.lottery.infrastructure.dao.IStrategyDetailDao;
import com.xbw.lottery.infrastructure.po.Award;
import com.xbw.lottery.infrastructure.po.Strategy;
import com.xbw.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.xbw.lottery.domain.strategy.model.vo.AwardBriefVO;

import javax.annotation.Resource;
import java.util.ArrayList;
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

        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }

        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
    }

    @Override
    public AwardBriefVO queryAwardInfo(Long awardId) {
        Award award = awardDao.queryAwardInfo(awardId);

        AwardBriefVO awardBriefVO = new AwardBriefVO();
        BeanUtils.copyProperties(award, awardBriefVO);

        return awardBriefVO;
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

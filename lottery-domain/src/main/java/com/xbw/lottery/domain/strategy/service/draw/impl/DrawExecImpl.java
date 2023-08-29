package com.xbw.lottery.domain.strategy.service.draw.impl;

import com.google.gson.Gson;
import com.xbw.lottery.domain.strategy.model.aggregate.StrategyRich;
import com.xbw.lottery.domain.strategy.model.req.DrawReq;
import com.xbw.lottery.domain.strategy.model.res.DrawResult;
import com.xbw.lottery.domain.strategy.repository.IStrategyRepository;
import com.xbw.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.xbw.lottery.domain.strategy.service.draw.AbstractDrawBase;
import com.xbw.lottery.infrastructure.po.Award;
import com.xbw.lottery.infrastructure.po.Strategy;
import com.xbw.lottery.infrastructure.po.StrategyDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("drawExec")
@Slf4j
public class DrawExecImpl extends AbstractDrawBase{

    Gson gson = new Gson();

    @Override
    protected List<Long> queryExcludedAwardIdList(Long strategyId) {
        List<Long> excludedAwardIdList = strategyRepository.queryNoStockStrategyAwardIdList(strategyId);
        log.info("抽奖策略 strategyId：{}， 无库存排除奖品ID列表excludedAwardIdList：{} ",strategyId, gson.toJson(excludedAwardIdList));
        return excludedAwardIdList;
    }

    @Override
    protected Long drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<Long> excludedAwardIdList) {
        // 进行抽奖
        Long awardId = drawAlgorithm.randomDraw(strategyId, excludedAwardIdList);

        // 判断抽奖结果
        if (null == awardId) {
            return null;
        }

        /*
         * 扣减库存，暂时采用数据库行级锁的方式进行扣减库存，后续优化为 Redis 分布式锁扣减 decr/incr
         * 注意：通常数据库直接锁行记录的方式并不能支撑较大体量的并发，但此种方式需要了解，
         * 因为在分库分表下的正常数据流量下的个人数据记录中，是可以使用行级锁的，因为他只影响到自己的记录，不会影响到其他人
         */
        boolean deductSuccess = strategyRepository.deductStock(strategyId, awardId);

        // 返回结果，库存扣减成功返回奖品ID，否则返回NULL 「在实际的业务场景中，如果中奖奖品库存为空，则会发送兜底奖品，比如各类券」
        return deductSuccess ? awardId : null;
    }
}

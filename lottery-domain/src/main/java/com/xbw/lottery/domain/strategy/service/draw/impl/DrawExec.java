package com.xbw.lottery.domain.strategy.service.draw.impl;

import cn.hutool.crypto.asymmetric.SM2;
import com.xbw.lottery.domain.strategy.model.aggregate.StrategyRich;
import com.xbw.lottery.domain.strategy.model.req.DrawReq;
import com.xbw.lottery.domain.strategy.model.res.DrawResult;
import com.xbw.lottery.domain.strategy.repository.IStrategyRepository;
import com.xbw.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.xbw.lottery.domain.strategy.service.draw.DrawBase;
import com.xbw.lottery.domain.strategy.service.draw.IDrawExec;
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
public class DrawExec extends DrawBase implements IDrawExec {

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExec(DrawReq drawReq) {
        log.info("抽奖开始，strategyId：{}", drawReq.getStrategyId());

        // 获取抽奖策略配置数据
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(drawReq.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();

        // 检验和初始化数据
        checkAndInitRateData(strategy.getId(), strategy.getStrategyMode(), strategyDetailList);

        // 获取抽奖策略
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        // 根据抽奖策略抽奖
        Long awardId = drawAlgorithm.randomDraw(strategy.getId(), new ArrayList<>());

        Award award = strategyRepository.queryAwardInfo(awardId);
        log.info("执行结果，中奖用户：{}， 奖品：{} 奖品ID：{}， 奖品名称：{}", drawReq.getUserId(), awardId, award.getAwardName());

        return new DrawResult(drawReq.getUserId(), strategy.getId(), awardId, award.getAwardName());
    }
}

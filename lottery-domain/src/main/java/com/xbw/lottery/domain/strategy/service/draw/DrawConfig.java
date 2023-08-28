package com.xbw.lottery.domain.strategy.service.draw;

import com.xbw.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽奖的策略配置类，使用的设计模式是策略模式
 * 这种配置可以轻松地扩展和修改，以适应不同的策略需求，而无需修改核心抽奖逻辑。
 */
public class DrawConfig {

    @Resource(name = "singleRateRandomDrawAlgorithm")
    private IDrawAlgorithm SingleRateRandomDrawAlgorithm;

    @Resource(name = "defaultRateRandomDrawAlgorithm")
    private IDrawAlgorithm DefaultRateRandomDrawAlgorithm;

    // StrategyMode -> IDrawAlgorithm
    // 使用 drawAlgorithmMap 来将不同的策略模式（用整数表示）与相应的抽奖算法关联起来
    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(1, DefaultRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(2, SingleRateRandomDrawAlgorithm);
    }


}

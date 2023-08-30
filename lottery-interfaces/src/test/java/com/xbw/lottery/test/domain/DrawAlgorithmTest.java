package com.xbw.lottery.test.domain;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.xbw.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DrawAlgorithmTest {

//    @Resource(name = "defaultRateRandomDrawAlgorithm")
    @Resource(name = "singleRateRandomDrawAlgorithm")
    private IDrawAlgorithm randomDrawAlgorithm;

    @Before
    public void init() {
        // 奖品信息
        List<AwardRateInfo> strategyList = new ArrayList<>();
        strategyList.add(new AwardRateInfo(1001L, new BigDecimal("0.05")));
        strategyList.add(new AwardRateInfo(1002L, new BigDecimal("0.15")));
        strategyList.add(new AwardRateInfo(1003L, new BigDecimal("0.20")));
        strategyList.add(new AwardRateInfo(1004L, new BigDecimal("0.25")));
        strategyList.add(new AwardRateInfo(1005L, new BigDecimal("0.35")));

        // 初始数据
        randomDrawAlgorithm.initRateTuple(100001L, Constants.StrategyMode.SINGLE.getCode(), strategyList);
    }

    @Test
    public void test_randomDrawAlgorithm() {

        List<Long> excludeAwardIds = new ArrayList<>();
        excludeAwardIds.add(1002L);
        excludeAwardIds.add(1004L);

        for (int i = 0; i < 20; i++) {
            log.info("中奖结果：" + randomDrawAlgorithm.randomDraw(100001L, excludeAwardIds));
        }

    }

}
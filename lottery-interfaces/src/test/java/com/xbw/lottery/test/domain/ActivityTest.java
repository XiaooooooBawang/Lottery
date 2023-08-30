package com.xbw.lottery.test.domain;

import com.google.gson.Gson;
import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.xbw.lottery.domain.activity.model.req.ActivityConfigReq;
import com.xbw.lottery.domain.activity.model.vo.ActivityVO;
import com.xbw.lottery.domain.activity.model.vo.AwardVO;
import com.xbw.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.xbw.lottery.domain.activity.model.vo.StrategyVO;
import com.xbw.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.xbw.lottery.domain.activity.service.stateflow.IStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 活动领域测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActivityTest {

    @Resource
    private IActivityDeploy activityDeploy;

    @Resource
    private IStateHandler stateHandler;

    private ActivityConfigRich activityConfigRich;

    /**
     * TODO：后面编写ID生成策略
     */
    private Long activityId = 120981321L;

    Gson gson = new Gson();

    @Before
    public void init() {

        ActivityVO activity = new ActivityVO();
        activity.setActivityId(activityId);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("测试活动描述");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(Constants.ActivityState.EDIT.getCode());
        activity.setCreator("xiaofuge");

        StrategyVO strategy = new StrategyVO();
        strategy.setStrategyId(10002L);
        strategy.setStrategyDesc("抽奖策略");
        strategy.setStrategyMode(Constants.StrategyMode.SINGLE.getCode());
        strategy.setGrantType(1);
        strategy.setGrantDate(new Date());
        strategy.setExtInfo("");

        StrategyDetailVO strategyDetail_01 = new StrategyDetailVO();
        strategyDetail_01.setStrategyId(strategy.getStrategyId());
        strategyDetail_01.setAwardId("101");
        strategyDetail_01.setAwardName("一等奖");
        strategyDetail_01.setAwardCount(10);
        strategyDetail_01.setAwardSurplusCount(10);
        strategyDetail_01.setAwardRate(new BigDecimal("0.05"));

        StrategyDetailVO strategyDetail_02 = new StrategyDetailVO();
        strategyDetail_02.setStrategyId(strategy.getStrategyId());
        strategyDetail_02.setAwardId("102");
        strategyDetail_02.setAwardName("二等奖");
        strategyDetail_02.setAwardCount(20);
        strategyDetail_02.setAwardSurplusCount(20);
        strategyDetail_02.setAwardRate(new BigDecimal("0.15"));

        StrategyDetailVO strategyDetail_03 = new StrategyDetailVO();
        strategyDetail_03.setStrategyId(strategy.getStrategyId());
        strategyDetail_03.setAwardId("103");
        strategyDetail_03.setAwardName("三等奖");
        strategyDetail_03.setAwardCount(50);
        strategyDetail_03.setAwardSurplusCount(50);
        strategyDetail_03.setAwardRate(new BigDecimal("0.20"));

        StrategyDetailVO strategyDetail_04 = new StrategyDetailVO();
        strategyDetail_04.setStrategyId(strategy.getStrategyId());
        strategyDetail_04.setAwardId("104");
        strategyDetail_04.setAwardName("四等奖");
        strategyDetail_04.setAwardCount(100);
        strategyDetail_04.setAwardSurplusCount(100);
        strategyDetail_04.setAwardRate(new BigDecimal("0.25"));

        StrategyDetailVO strategyDetail_05 = new StrategyDetailVO();
        strategyDetail_05.setStrategyId(strategy.getStrategyId());
        strategyDetail_05.setAwardId("104");
        strategyDetail_05.setAwardName("五等奖");
        strategyDetail_05.setAwardCount(500);
        strategyDetail_05.setAwardSurplusCount(500);
        strategyDetail_05.setAwardRate(new BigDecimal("0.35"));

        List<StrategyDetailVO> strategyDetailList = new ArrayList<>();
        strategyDetailList.add(strategyDetail_01);
        strategyDetailList.add(strategyDetail_02);
        strategyDetailList.add(strategyDetail_03);
        strategyDetailList.add(strategyDetail_04);
        strategyDetailList.add(strategyDetail_05);

        strategy.setStrategyDetailList(strategyDetailList);

        AwardVO award_01 = new AwardVO();
        award_01.setAwardId(101L);
        award_01.setAwardType(Constants.AwardType.DESC.getCode());
        award_01.setAwardName("电脑");
        award_01.setAwardContent("请联系活动组织者 fustack");

        AwardVO award_02 = new AwardVO();
        award_02.setAwardId(102L);
        award_02.setAwardType(Constants.AwardType.DESC.getCode());
        award_02.setAwardName("手机");
        award_02.setAwardContent("请联系活动组织者 fustack");

        AwardVO award_03 = new AwardVO();
        award_03.setAwardId(103L);
        award_03.setAwardType(Constants.AwardType.DESC.getCode());
        award_03.setAwardName("平板");
        award_03.setAwardContent("请联系活动组织者 fustack");

        AwardVO award_04 = new AwardVO();
        award_04.setAwardId(104L);
        award_04.setAwardType(Constants.AwardType.DESC.getCode());
        award_04.setAwardName("耳机");
        award_04.setAwardContent("请联系活动组织者 fustack");

        AwardVO award_05 = new AwardVO();
        award_05.setAwardId(105L);
        award_05.setAwardType(Constants.AwardType.DESC.getCode());
        award_05.setAwardName("数据线");
        award_05.setAwardContent("请联系活动组织者 fustack");

        List<AwardVO> awardList = new ArrayList<>();
        awardList.add(award_01);
        awardList.add(award_02);
        awardList.add(award_03);
        awardList.add(award_04);
        awardList.add(award_05);

        activityConfigRich = new ActivityConfigRich(activity, strategy, awardList);
    }

    @Test
    public void test_createActivity() {
        activityDeploy.createActivity(new ActivityConfigReq(activityId, activityConfigRich));
    }

    @Test
    public void test_alterState() {
        log.info("提交审核，测试：{}", gson.toJson(stateHandler.arraignment(100001L, Constants.ActivityState.EDIT)));
        log.info("审核通过，测试：{}", gson.toJson(stateHandler.checkPass(100001L, Constants.ActivityState.ARRAIGNMENT)));
        log.info("运行活动，测试：{}", gson.toJson(stateHandler.doing(100001L, Constants.ActivityState.PASS)));
        log.info("二次提审，测试：{}", gson.toJson(stateHandler.checkPass(100001L, Constants.ActivityState.EDIT)));
    }

}
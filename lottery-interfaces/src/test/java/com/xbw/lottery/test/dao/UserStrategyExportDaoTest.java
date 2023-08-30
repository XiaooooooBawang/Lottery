package com.xbw.lottery.test.dao;


import com.google.gson.Gson;
import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.support.ids.IIdGenerator;
import com.xbw.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.xbw.lottery.infrastructure.po.UserStrategyExport;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 用户策略计算结果表Dao测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserStrategyExportDaoTest {


    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    Gson gson = new Gson();

    @Test
    public void test_insert() {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId("Uhdgkw766120d");
        userStrategyExport.setActivityId(idGeneratorMap.get(Constants.Ids.SHORT_CODE).nextId());
        userStrategyExport.setOrderId(idGeneratorMap.get(Constants.Ids.SNOWFLAKE).nextId());
        userStrategyExport.setStrategyId(idGeneratorMap.get(Constants.Ids.RANDOM_NUMERIC).nextId());
        userStrategyExport.setStrategyMode(Constants.StrategyMode.SINGLE.getCode());
        userStrategyExport.setGrantType(1);
        userStrategyExport.setGrantDate(new Date());
        userStrategyExport.setGrantState(1);
        userStrategyExport.setAwardId(1L);
        userStrategyExport.setAwardType(Constants.AwardType.DESC.getCode());
        userStrategyExport.setAwardName("IMac");
        userStrategyExport.setAwardContent("奖品描述");
        userStrategyExport.setUuid(String.valueOf(userStrategyExport.getOrderId()));

        userStrategyExportDao.insert(userStrategyExport);
    }

    @Test
    public void test_select() {
        UserStrategyExport userStrategyExport = userStrategyExportDao.queryUserStrategyExportByUId("Uhdgkw766120d");
        log.info("测试结果：{}", gson.toJson(userStrategyExport));
    }

}

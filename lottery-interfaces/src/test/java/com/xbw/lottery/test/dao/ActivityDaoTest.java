package com.xbw.lottery.test.dao;

import com.google.gson.Gson;
import com.xbw.lottery.infrastructure.dao.IActivityDao;
import com.xbw.lottery.infrastructure.po.Activity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试活动表 DAO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActivityDaoTest {

    @Resource
    private IActivityDao activityDao;


    Gson gson = new Gson();

    @Test
    public void test_queryActivityById() {
        Activity activity = activityDao.queryActivityById(100001L);
        log.info("测试结果：{}", gson.toJson(activity));
    }

}

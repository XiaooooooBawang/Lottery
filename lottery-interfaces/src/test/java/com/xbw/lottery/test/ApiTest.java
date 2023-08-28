package com.xbw.lottery.test;

import com.google.gson.Gson;
import com.xbw.lottery.infrastructure.dao.IActivityDao;
import com.xbw.lottery.infrastructure.po.Activity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ApiTest {

    @Resource
    private IActivityDao activityDao;

    public Long activityId = 10001L;

    Gson gson = new Gson();

    @Test
    public void testInsert() {
        Activity activity = new Activity();
        activity.setActivityId(activityId);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("测试用例");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(10);
        activity.setTakeCount(10);
        activity.setActivityState(0);
        activity.setCreator("xbw");
        activityDao.insert(activity);
    }

    @Test
    public void testQuery() {
        Activity activity = activityDao.queryActivityById(activityId);
        log.info("测试结果：{}", gson.toJson(activity));
    }
}

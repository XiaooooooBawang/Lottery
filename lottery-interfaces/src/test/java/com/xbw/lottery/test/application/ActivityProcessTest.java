package com.xbw.lottery.test.application;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xbw.lottery.application.process.IActivityProcess;
import com.xbw.lottery.application.process.req.DrawProcessReq;
import com.xbw.lottery.application.process.res.DrawProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActivityProcessTest {


    @Resource
    private IActivityProcess activityProcess;

    Gson gson = new Gson();

    @Test
    public void test_doDrawProcess() {
        DrawProcessReq req = new DrawProcessReq();
        req.setuId("sfdgfd");
        req.setActivityId(100001L);
        DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(req);

        log.info("请求入参：{}", gson.toJson(req));
        log.info("测试结果：{}", gson.toJson(drawProcessResult));
    }

}

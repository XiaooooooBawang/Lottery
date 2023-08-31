package com.xbw.lottery.test.domain;


import com.alibaba.fastjson.JSON;
import com.xbw.lottery.domain.rule.model.req.DecisionMatterReq;
import com.xbw.lottery.domain.rule.model.res.EngineResult;
import com.xbw.lottery.domain.rule.service.engine.EngineFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 规则引擎测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RuleTest {



    @Resource
    private EngineFilter engineFilter;

    @Test
    public void test_process() {
        DecisionMatterReq req = new DecisionMatterReq();
        req.setTreeId(2110081902L);
        req.setUserId("fustack");
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "25");
        }});

        EngineResult res = engineFilter.process(req);

        log.info("请求参数：{}", JSON.toJSONString(req));
        log.info("测试结果：{}", JSON.toJSONString(res));
    }

}

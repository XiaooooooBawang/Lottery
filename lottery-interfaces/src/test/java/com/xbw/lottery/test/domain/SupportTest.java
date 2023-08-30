package com.xbw.lottery.test.domain;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.support.ids.IIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 支撑领域测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SupportTest {


    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Test
    public void test_ids() {
        log.info("雪花算法策略，生成ID：{}", idGeneratorMap.get(Constants.Ids.SNOWFLAKE).nextId());
        log.info("随机算法策略，生成ID：{}", idGeneratorMap.get(Constants.Ids.RANDOM_NUMERIC).nextId());
        log.info("日期算法策略，生成ID：{}", idGeneratorMap.get(Constants.Ids.SHORT_CODE).nextId());

    }

}

package com.xbw.lottery.domain.support.ids;


import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.support.ids.policy.RandomNumeric;
import com.xbw.lottery.domain.support.ids.policy.ShortCode;
import com.xbw.lottery.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 把策略生成ID服务包装到 Map<Constants.Ids, IIdGenerator> 对象中
 * Id 策略模式上下文配置「在正式的完整的系统架构中，ID 的生成会有单独的服务来完成，其他服务来调用 ID 生成接口即可」
 * 也是策略模式，只是配置方法使用了上下文配置 @Configuration + @Bean
 */
@Configuration
public class IdContext {

    /**
     * 创建 ID 生成策略对象，属于策略设计模式的使用方式
     *
     * @param snowFlake     雪花算法，长码，大量
     * @param shortCode     日期算法，短码，少量，全局唯一需要自己保证
     * @param randomNumeric 随机算法，短码，大量，全局唯一需要自己保证
     * @return IIdGenerator 实现类
     */
    @Bean
    public Map<Constants.Ids, IIdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric randomNumeric) {
        Map<Constants.Ids, IIdGenerator> idGeneratorMap = new HashMap<>();
        idGeneratorMap.put(Constants.Ids.SNOWFLAKE, snowFlake);
        idGeneratorMap.put(Constants.Ids.SHORT_CODE, shortCode);
        idGeneratorMap.put(Constants.Ids.RANDOM_NUMERIC, randomNumeric);
        return idGeneratorMap;
    }

}

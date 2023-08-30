package com.xbw.lottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

import com.xbw.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * hutool 工具包下的雪花算法，
 * 15位雪花算法推荐：https://github.com/yitter/idgenerator/blob/master/Java/source/src/main/java/com/github/yitter/core/SnowWorkerM1.java
 */
@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        // 0 ~ 31 位，可以采用配置的方式使用，使用ipv4地址来生成
        long id;
        try {
            id = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            id = NetUtil.getLocalhostStr().hashCode();
        }

        id = id >> 16 & 31;

        snowflake =IdUtil.getSnowflake(id, 1L);
    }


    // 要保证全局唯一，需要加锁
    @Override
    public synchronized long nextId() {
        return snowflake.nextId();
    }

}

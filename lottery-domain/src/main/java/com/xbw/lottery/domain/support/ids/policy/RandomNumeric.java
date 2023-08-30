package com.xbw.lottery.domain.support.ids.policy;


import com.xbw.lottery.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;


/**
 * 工具类生成 org.apache.commons.lang3.RandomStringUtils
 */
@Component
public class RandomNumeric implements IIdGenerator {

    // 随机生成11为的id
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }

}

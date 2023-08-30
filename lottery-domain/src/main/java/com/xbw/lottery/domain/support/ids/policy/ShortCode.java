package com.xbw.lottery.domain.support.ids.policy;


import com.xbw.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Random;

/**
 * 短码生成策略，仅支持很小的调用量，用于生成活动配置类编号，保证全局唯一
 */
@Component
public class ShortCode implements IIdGenerator {

    // 要保证全局唯一，需要加锁
    @Override
    public synchronized long nextId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // 打乱排序：2022年为准 + 小时 + 周 + 日 + 三位随机数
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year - 2022);
        stringBuilder.append(hour);
        // 左补0保留两位
        stringBuilder.append(String.format("%02d", week));
        stringBuilder.append(day);
        // 左补0保留三位
        stringBuilder.append(String.format("%03d", new Random().nextInt(1000)));

        return Long.parseLong(stringBuilder.toString());
    }

}

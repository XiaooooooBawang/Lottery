package com.xbw.lottery.domain.support.ids;


/**
 * 生成ID接口
 */
public interface IIdGenerator {

    /**
     * 获取ID，目前有两种实现方式
     * 1. 雪花算法，用于生成单号，唯一，大量，分库分表
     * 2. 日期算法，用于生成活动编号类，特性是生成数字串较短，但指定时间内不能生成太多
     * 3. 随机算法
     *
     * @return ID
     */
    long nextId();
}

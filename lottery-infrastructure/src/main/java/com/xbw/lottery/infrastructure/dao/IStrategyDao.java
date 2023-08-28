package com.xbw.lottery.infrastructure.dao;

import com.xbw.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyDao {
    Strategy queryStrategy(Long strategyId);
}

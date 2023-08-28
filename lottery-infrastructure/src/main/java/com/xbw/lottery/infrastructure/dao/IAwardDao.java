package com.xbw.lottery.infrastructure.dao;

import com.xbw.lottery.infrastructure.po.Activity;
import com.xbw.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAwardDao {
    Award queryAward(Long awardId);
}

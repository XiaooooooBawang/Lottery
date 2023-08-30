package com.xbw.lottery.infrastructure.dao;

import com.xbw.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAwardDao {

    /**
     * 查询奖品信息
     *
     * @param awardId 奖品ID
     * @return        奖品信息
     */
    Award queryAwardInfo(Long awardId);

    /**
     * 插入奖品配置
     *
     * @param awardList 奖品配置
     */
    void insertAwardList(List<Award> awardList);
}

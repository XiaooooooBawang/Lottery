package com.xbw.lottery.domain.award.service.goods;

import com.xbw.lottery.domain.award.repository.IAwardRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * 配送货物基础共用类
 */
@Slf4j
public class DistributionBase {


    @Resource
    private IAwardRepository awardRepository;

    protected void updateUserAwardDistributionState(String uId, String orderId, Long awardId,
                                                    Integer distributionState, String distributionStateInfo) {
        // TODO 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态
        log.info("TODO 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态 uId：{}", uId);
    }

}

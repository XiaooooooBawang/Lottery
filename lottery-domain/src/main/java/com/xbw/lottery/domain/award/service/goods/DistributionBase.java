package com.xbw.lottery.domain.award.service.goods;

import com.xbw.lottery.domain.award.repository.IOrderRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * 配送货物基础共用类
 */
@Slf4j
public class DistributionBase {


    @Resource
    private IOrderRepository awardRepository;

    protected void updateUserAwardGrantState(String uId, Long orderId, Long awardId,
                                             Integer grantState) {
        awardRepository.updateUserAwardGrantState(uId, orderId, awardId, grantState);
    }

}

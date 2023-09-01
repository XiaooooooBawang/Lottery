package com.xbw.lottery.infrastructure.repository;


import com.xbw.lottery.domain.award.repository.IOrderRepository;
import com.xbw.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.xbw.lottery.infrastructure.po.UserStrategyExport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 奖品表仓储服务
 */
@Repository
public class OrderRepository implements IOrderRepository {
    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public void updateUserAwardGrantState(String uId, Long orderId, Long awardId, Integer grantState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(grantState);
        userStrategyExportDao.updateUserAwardGrantState(userStrategyExport);
    }

}

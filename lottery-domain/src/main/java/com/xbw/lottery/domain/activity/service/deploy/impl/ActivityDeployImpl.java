package com.xbw.lottery.domain.activity.service.deploy.impl;

import com.google.gson.Gson;
import com.xbw.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.xbw.lottery.domain.activity.model.req.ActivityConfigReq;
import com.xbw.lottery.domain.activity.repository.IActivityRepository;
import com.xbw.lottery.domain.activity.service.deploy.IActivityDeploy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Slf4j
public class ActivityDeployImpl implements IActivityDeploy {

    @Resource
    private IActivityRepository activityRepository;

    Gson gson = new Gson();


    // 配置事务
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createActivity(ActivityConfigReq req) {
        log.info("创建活动配置开始，activityId：{}", req.getActivityId());

        // 获取聚合对象
        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();
        try {
            // 添加活动配置
            activityRepository.addActivity(activityConfigRich.getActivity());

            // 添加奖品配置
            activityRepository.addAwardList(activityConfigRich.getAwardList());

            // 添加策略配置
            activityRepository.addStrategy(activityConfigRich.getStrategy());

            // 添加策略明细配置
            activityRepository.addStrategyDetailList(activityConfigRich.getStrategy().getStrategyDetailList());

            log.info("创建活动配置完成，activityId：{}", req.getActivityId());
        } catch (DuplicateKeyException e) {
            log.error("创建活动配置失败，唯一索引冲突 activityId：{} reqJson：{}", req.getActivityId(), gson.toJson(req), e);
            throw e;
        }
    }

    @Override
    public void updateActivity(ActivityConfigReq req) {
        // TODO: 非核心功能后续补充
    }
}

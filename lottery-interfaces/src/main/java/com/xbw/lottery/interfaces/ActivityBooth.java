package com.xbw.lottery.interfaces;

import com.xbw.lottery.common.Constant;
import com.xbw.lottery.common.Result;
import com.xbw.lottery.infrastructure.dao.IActivityDao;
import com.xbw.lottery.infrastructure.po.Activity;
import com.xbw.lottery.rpc.IActivityBooth;
import com.xbw.lottery.rpc.dto.ActivityDto;
import com.xbw.lottery.rpc.req.ActivityReq;
import com.xbw.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;


@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq activityReq) {

        Activity activity = activityDao.queryActivityById(activityReq.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constant.ResponseCode.SUCCESS.getCode(), Constant.ResponseCode.SUCCESS.getInfo()), activityDto);
    }
}

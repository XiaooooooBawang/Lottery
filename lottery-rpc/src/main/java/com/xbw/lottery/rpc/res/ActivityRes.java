package com.xbw.lottery.rpc.res;

import com.xbw.lottery.common.Result;
import com.xbw.lottery.rpc.dto.ActivityDto;

import java.io.Serializable;

/**
 * 活动配置 result
 */
public class ActivityRes implements Serializable {
    private ActivityDto activity;

    private Result result;

    public ActivityRes() {
    }

    public ActivityRes(Result result, ActivityDto activity) {
        this.result = result;
        this.activity = activity;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
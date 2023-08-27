package com.xbw.lottery.rpc.req;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动配置 request
 */
public class ActivityReq implements Serializable {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动ID
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * 活动ID
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }


}
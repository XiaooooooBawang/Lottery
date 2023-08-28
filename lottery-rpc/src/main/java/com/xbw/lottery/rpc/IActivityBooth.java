package com.xbw.lottery.rpc;

import com.xbw.lottery.rpc.req.ActivityReq;
import com.xbw.lottery.rpc.res.ActivityRes;

public interface IActivityBooth {
    ActivityRes queryActivityById(ActivityReq activityReq);
}

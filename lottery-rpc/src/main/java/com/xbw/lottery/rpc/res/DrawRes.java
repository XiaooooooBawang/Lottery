package com.xbw.lottery.rpc.res;


import com.xbw.lottery.common.Result;
import com.xbw.lottery.rpc.dto.AwardDTO;

import java.io.Serializable;

/**
 * 抽奖结果
 */
public class DrawRes extends Result implements Serializable {

    private AwardDTO awardDTO;

    public DrawRes(Integer code, String info) {
        super(code, info);
    }

    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }

}

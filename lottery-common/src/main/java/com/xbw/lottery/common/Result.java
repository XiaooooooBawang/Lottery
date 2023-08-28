package com.xbw.lottery.common;

import java.io.Serializable;

/**
 * 统一返回对象中，Code码、Info描述
 */
public class Result implements Serializable {

    private String code;

    private String info;

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

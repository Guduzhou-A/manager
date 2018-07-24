package com.baicells.manager.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一API响应结果封装
 */
public class Result {
    private int code;
    private String message;
    private Object data ;


    public int getCode() {
        return code;
    }

    public void setCode( ResultCode resultCode) {
        this.code = resultCode.code();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

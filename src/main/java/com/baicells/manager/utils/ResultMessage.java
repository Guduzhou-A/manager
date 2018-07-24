package com.baicells.manager.utils;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultMessage {
    SUCCESS("执行成功"),//成功
    FAIL("执行失败"),//失败
    UNAUTHORIZED("未认证"),//未认证（签名错误）
    NOT_FOUND("接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR("服务器内部错误");//服务器内部错误



    private final String messagae;

    ResultMessage(String messagae) {
        this.messagae = messagae;
    }

    public String messagae() {
        return messagae;
    }


}

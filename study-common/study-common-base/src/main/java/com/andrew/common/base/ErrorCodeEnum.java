package com.andrew.common.base;

/**
 * @Author bo.fang
 * @Description
 * @Date 11:49 下午 2020/9/17
 */
public enum ErrorCodeEnum {

    SUCCESS("S00000", "请求成功"),
    FAIL("F00000", "请求异常"),
    PARAMS_VALID_FAIL("F00001", "参数校验异常");
    private String code;

    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

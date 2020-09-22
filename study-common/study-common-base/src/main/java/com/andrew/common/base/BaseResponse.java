package com.andrew.common.base;

/**
 * @Author bo.fang
 * @Description
 * @Date 11:19 下午 2020/9/17
 */
public class BaseResponse {

    private String code;

    private String msg;

    private Object data;

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String msg) {
        this.msg = msg;
    }

    public BaseResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResponse create(String code, String msg, Object data) {
        return new BaseResponse(code, msg, data);
    }

    public static BaseResponse success(Object data) {
        return new BaseResponse(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMsg(), data);
    }

    public static BaseResponse success(String msg, Object data) {
        return new BaseResponse(ErrorCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static BaseResponse error() {
        return new BaseResponse(ErrorCodeEnum.FAIL.getCode(), ErrorCodeEnum.FAIL.getMsg());
    }

    public static BaseResponse error(String code, String msg) {
        return new BaseResponse(code, msg);
    }

    public static BaseResponse error(String msg) {
        return new BaseResponse(ErrorCodeEnum.FAIL.getCode(), msg);
    }
}

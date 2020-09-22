package com.andrew.common.base;

/**
 * @Author bo.fang
 * @Description
 * @Date 10:57 下午 2020/9/17
 */
public class BusinessWarnException extends RuntimeException {

    private String code;

    private String errorMsg;

    public BusinessWarnException(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BusinessWarnException(String message, String code, String errorMsg) {
        super(message);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BusinessWarnException(String message, Throwable cause, String code, String errorMsg) {
        super(message, cause);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BusinessWarnException(Throwable cause, String code, String errorMsg) {
        super(cause);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BusinessWarnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String errorMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

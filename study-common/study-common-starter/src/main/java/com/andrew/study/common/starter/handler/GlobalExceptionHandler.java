package com.andrew.study.common.starter.handler;

import com.andrew.common.base.BaseResponse;
import com.andrew.common.base.BusinessWarnException;
import com.andrew.common.base.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author bo.fang
 * @Description
 * @Date 10:29 下午 2020/9/17
 */
@RestControllerAdvice
@Slf4j
@ConditionalOnProperty(value = {
        "spring.global.exception.enable"
}, havingValue = "true")
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        log.info("project enable global exception handler");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = "";
        if (bindingResult.hasErrors()) {
            msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        return BaseResponse.error(ErrorCodeEnum.PARAMS_VALID_FAIL.getCode(), msg);
    }

    @ExceptionHandler(value = BusinessWarnException.class)
    public BaseResponse businessWarnExceptionHandler(BusinessWarnException e) {
        log.info("GlobalExceptionHandler.businessWarnExceptionHandler:code:{},errorMsg:{}", e.getCode(), e.getErrorMsg(), e);
        return BaseResponse.error(e.getCode(), e.getErrorMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse exceptionHandler(Exception e) {
        log.info("GlobalExceptionHandler.businessWarnExceptionHandler:errorMsg:{}", e.getMessage(), e);
        return BaseResponse.error(e.getMessage());
    }
}

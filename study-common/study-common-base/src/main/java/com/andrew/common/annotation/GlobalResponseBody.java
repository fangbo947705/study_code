package com.andrew.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author bo.fang
 * @Description
 * @Date 9:50 下午 2020/9/21
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalResponseBody {

    boolean ignoreNullFiled() default true;
}

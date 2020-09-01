package com.andrew.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

/**
 * @Author bo.fang
 * @Description
 * @Date 11:03 下午 2020/9/1
 */
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.ctx = applicationContext;
    }

    /**
     * get bean by class
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return ctx.getBean(clazz);
    }

    /**
     * get bean by name
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) ctx.getBean(name);
    }


    @Override
    public void destroy() throws Exception {
        ctx = null;
    }

    private static void checkApplicationContext() {
        if (Objects.isNull(ctx)) {
            throw new RuntimeException("applicationContext is null ,please check if it has been injected success");
        }
    }
}

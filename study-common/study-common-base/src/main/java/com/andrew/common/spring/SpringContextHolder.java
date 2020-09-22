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

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
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
        return applicationContext.getBean(clazz);
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
        return (T) applicationContext.getBean(name);
    }


    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }

    private static void checkApplicationContext() {
        if (Objects.isNull(applicationContext)) {
            throw new RuntimeException("applicationContext is null ,please check if it has been injected success");
        }
    }
}

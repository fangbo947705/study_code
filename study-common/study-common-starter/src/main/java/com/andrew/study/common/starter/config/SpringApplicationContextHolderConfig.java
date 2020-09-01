package com.andrew.study.common.starter.config;

import com.andrew.common.spring.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author bo.fang
 * @Description
 * @Date 11:13 下午 2020/9/1
 */
@Configuration
public class SpringApplicationContextHolderConfig {

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}

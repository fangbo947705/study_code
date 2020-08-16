package com.andrew.study.common.starter.config;

import com.andrew.common.filter.MdcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Collections;

/**
 * @Author bo.fang
 * @Description
 * @Date 4:27 下午 2020/8/16
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> filterFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new MdcFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.setUrlPatterns(Collections.singleton("/*"));
        return filterFilterRegistrationBean;
    }
}

package com.andrew.study;

import com.andrew.study.filter.MdcFilter;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author andrew
 */
@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@MapperScan(basePackages = "com.andrew.study.mapper")
@EnableTransactionManagement
@ServletComponentScan(basePackages = "com.andrew.study.filter")
@EnableEurekaClient
public class StudyApplication {

    @Resource
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

    @Bean(name = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(50);
        threadPoolTaskExecutor.setThreadNamePrefix("custom-async-task-executor");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.setQueueCapacity(1000);
        threadPoolTaskExecutor.setKeepAliveSeconds(30);
        return threadPoolTaskExecutor;
    }

    @Bean("sqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        return mybatisSqlSessionFactoryBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MdcFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}

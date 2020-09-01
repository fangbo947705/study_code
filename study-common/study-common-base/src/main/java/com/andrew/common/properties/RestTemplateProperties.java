package com.andrew.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author bo.fang
 * @Description
 * @Date 5:24 下午 2020/8/16
 */
@ConfigurationProperties(prefix = "rest.template")
@Configuration
@Data
public class RestTemplateProperties {

    /**
     * pool max total
     */
    private int maxTotal = 200;

    /**
     * default max per route
     */
    private int defaultMaxPerRoute = 30;

    /**
     * socketTimeout
     */
    private int socketTimeout = 10000;

    /**
     * connectTimeout
     */
    private int connectTimeout = 3000;

    /**
     * connectionRequestTimeout
     */
    private int connectionRequestTimeout = 1000;

}

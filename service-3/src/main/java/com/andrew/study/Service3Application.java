package com.andrew.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author bo.fang
 * @Description
 * @Date 2:35 上午 2020/8/15
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Service3Application {

    public static void main(String[] args) {
        SpringApplication.run(Service3Application.class, args);
    }
}

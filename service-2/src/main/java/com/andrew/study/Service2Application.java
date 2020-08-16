package com.andrew.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author bo.fang
 * @Description
 * @Date 2:35 上午 2020/8/15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Service2Application {

    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }
}

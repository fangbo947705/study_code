package com.andrew.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author bo.fang
 * @Description
 * @Date 7:46 上午 2020/8/4
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosGatewayApplication.class, args);
    }
}

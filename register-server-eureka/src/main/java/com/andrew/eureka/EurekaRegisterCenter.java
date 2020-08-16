package com.andrew.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author bo.fang
 * @Description
 * @Date 1:08 下午 2020/8/3
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaRegisterCenter {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegisterCenter.class, args);
    }
}

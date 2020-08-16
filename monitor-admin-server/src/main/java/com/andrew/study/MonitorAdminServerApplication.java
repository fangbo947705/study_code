package com.andrew.study;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author bo.fang
 * @Description
 * @Date 10:29 下午 2020/8/4
 */
@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class MonitorAdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorAdminServerApplication.class, args);
    }
}

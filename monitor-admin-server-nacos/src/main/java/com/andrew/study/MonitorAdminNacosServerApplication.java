package com.andrew.study;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author bo.fang
 * @Description
 * @Date 10:29 下午 2020/8/4
 */
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class MonitorAdminNacosServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorAdminNacosServerApplication.class, args);
    }
}

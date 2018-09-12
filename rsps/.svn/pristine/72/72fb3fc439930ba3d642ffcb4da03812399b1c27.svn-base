package com.izhuixin.rsps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.izhuixin.rsps.dao.*", "com.izhuixin.rsps.common.dba"})
@EnableFeignClients
@EnableScheduling
public class ApiService {
    public static void main(String[] args) {
        SpringApplication.run(ApiService.class, args);
    }
}

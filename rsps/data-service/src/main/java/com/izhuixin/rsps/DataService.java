package com.izhuixin.rsps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.izhuixin.rsps.dao","mapper"})
@EnableFeignClients
public class DataService {
    public static void main(String[] args) {
        SpringApplication.run(DataService.class, args);
    }
}

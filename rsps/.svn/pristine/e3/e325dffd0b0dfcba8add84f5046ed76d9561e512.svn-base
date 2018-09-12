package com.izhuixin.rsps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean("poolTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPool(){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(1);
        poolTaskExecutor.setMaxPoolSize(2);
        poolTaskExecutor.setQueueCapacity(800);
        return poolTaskExecutor;
    }
}

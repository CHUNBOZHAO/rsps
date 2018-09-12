package com.izhuixin.rsps.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 每日效能信息生成（生成昨天的信息）
     */
//    @Scheduled(cron = "0/1 * * * * ?")
//    public void generateDailyBoxEfficiencyReport() {
//        logger.info("生成包装箱效能信息报表");
//    }

}

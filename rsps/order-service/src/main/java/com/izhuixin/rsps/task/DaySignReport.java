package com.izhuixin.rsps.task;

import com.izhuixin.rsps.dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class DaySignReport {

    @Autowired
    private OrderDao orderDao;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    //一天
    @Scheduled(cron = "0 0 0 * * ?")
    //分钟
//    @Scheduled(cron = "0 * * * * ?")
    public Integer getSignCount() throws Exception{

        List<String> listSigns = orderDao.selectDaySign();
        int m;
        int total = 0;
        for (String string : listSigns) {
            m = Integer.parseInt(string);
            total += m;
        }

        int n;
        int total2 = 0;
        List<String> listRefuesds = orderDao.selectDayRefused();
        for (String string : listRefuesds) {
            n = Integer.parseInt(string);
            total2 += n;
        }

        int count = total-total2;
        System.out.println(count);
        return count;

    }



}

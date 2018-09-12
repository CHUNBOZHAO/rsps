package com.izhuixin.test;

import com.izhuixin.rsps.OrderService;
import com.izhuixin.rsps.dao.OrderDao;
import com.izhuixin.rsps.domain.Order;
import com.izhuixin.rsps.utils.SpringContext;
import org.joda.time.DateTime;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.nio.charset.StandardCharsets;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderService.class)
@WebAppConfiguration
public class Test {

    @org.junit.Test
    public void test01(){

//        DateTime dateTime = new DateTime();
//
//        System.out.println(StandardCharsets.UTF_8.name());

//        Order order = new Order();
//        order.setRefusedCount("2");
//        order.setRefusedReason("2");
//        order.setOrderNo("1");
//        OrderDao orderDao = SpringContext.getBean(OrderDao.class);
//        orderDao.updateRefuseCountAndReason(order);
    }
}

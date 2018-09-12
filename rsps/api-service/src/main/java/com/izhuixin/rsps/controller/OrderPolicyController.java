package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.service.OrderPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/orderData/policy")
public class OrderPolicyController {

    @Autowired
    private OrderPolicyService orderPolicyService;

    @RequestMapping("/order/obtain")
    public void obtainOrder(String orderId, Double longitude, Double latitude) {
        orderPolicyService.obtainOrder(orderId, longitude, latitude);
    }

    @RequestMapping("/order/delivery")
    public void deliveryOrder(String orderId, String entCode, String boxId) {
        orderPolicyService.deliveryOrder(orderId, entCode, boxId);
    }
}

package com.izhuixin.rsps.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
@RequestMapping("v1/orderData/policy")
public interface OrderPolicyService {
    @RequestMapping("/order/obtain")
    void obtainOrder(@RequestParam("orderId") String orderId, @RequestParam("longitude") Double longitude, @RequestParam("latitude") Double latitude);

}

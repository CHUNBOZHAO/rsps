package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.domain.Order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "order-service")
public interface OrderFeignService {

    /**
     **保存订单信息
     * @param order
     * @return
     */
    @RequestMapping("v1/orderData/commonOrder/truckingOrder")
    boolean saveOrder(@RequestBody Order order);


}

package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.model.OrderSource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "order-service")
public interface OrderSourceFeignService {

    @RequestMapping("v1/orderData/commonOrderSource/save")
    boolean saveOrderSource(@RequestBody OrderSource orderSource);
}

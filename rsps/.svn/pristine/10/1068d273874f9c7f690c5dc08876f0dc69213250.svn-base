package com.izhuixin.rsps.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
public interface ApiFeignService {

    /**
     * 更新包装箱责任人（施必达提供）
     * @param orderId
     * @param operatorNo
     * @return
     */
    @RequestMapping("v1/orderData/box/operator/box/update")
    boolean updateBoxOperator(@RequestParam("orderId") String orderId, @RequestParam("operatorNo") String operatorNo, @RequestParam("entCode") String entCode);
}

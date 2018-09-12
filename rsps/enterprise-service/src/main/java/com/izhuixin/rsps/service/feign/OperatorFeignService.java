package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.domain.OperatorInfo;
import com.izhuixin.rsps.domain.Order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
public interface OperatorFeignService {

    /**
     **保存订单信息
     * @param
     * @return
     */
    @RequestMapping("/v1/operatorData/operatorInfo/userName/get")
    OperatorInfo getOperatorInfo(@RequestParam("userName") String userName, @RequestParam("entCode") String entCode);


}

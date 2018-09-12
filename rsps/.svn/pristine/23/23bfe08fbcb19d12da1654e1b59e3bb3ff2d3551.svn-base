package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.common.BoxFlowRecordVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
@RequestMapping("v1/orderData/driver")
public interface OrderDriverService {

    @RequestMapping("/flowRecord/get/{orderId}")
    BoxFlowRecordVO getFlowRecord(@RequestParam("orderId") String orderId);

}

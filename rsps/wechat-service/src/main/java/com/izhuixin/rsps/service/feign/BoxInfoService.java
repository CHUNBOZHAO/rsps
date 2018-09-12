package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.domain.BoxTypeInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "api-service")
@RequestMapping("v1/boxData/boxInfo")
public interface BoxInfoService {

    @RequestMapping("/boxTypes/get/{orderId}")
    List<BoxTypeInfo> getBoxTypes(@PathVariable("orderId") String orderId);

}

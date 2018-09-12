package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.common.WxConfigModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
@RequestMapping("v1/wxData/ticket")
public interface WeChatService {
    @RequestMapping("/signature/get")
    WxConfigModel getSignature(@RequestParam("url") String url);
}

package com.izhuixin.rsps.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth-center")
public interface TokenService {
    @RequestMapping(value = "/oauth/token",method = RequestMethod.POST)
    String getToken(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "grant_type") String grant_type, @RequestHeader(name = "Authorization", required = false) String auth);
}

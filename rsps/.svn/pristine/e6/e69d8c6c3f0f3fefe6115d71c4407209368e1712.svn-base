package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.domain.CustomInfoDO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
@RequestMapping("v1/customData/")
public interface CustomInfoService {

    @RequestMapping("/tel/check/{tel}")
    boolean checkTel(@RequestParam("tel") String tel);

    @RequestMapping("/save")
    boolean saveCustomInfo(@RequestParam("tel") String tel, @RequestParam("password") String password);

    @RequestMapping("/customInfo/get/{customId}")
    CustomInfoDO getCustomInfo(@RequestParam("customId") String customId);

    @RequestMapping("/password/modify")
    boolean modifyPwd(@RequestParam("id") Long id, @RequestParam("newPassword") String newPassword);

    @RequestMapping("/password/check")
    boolean checkPwd(@RequestParam("id") Long id, @RequestParam("oldPassword") String oldPassword);
}

package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.vo.web.BoxPara;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("coap-service")
//@RequestMapping("")
public interface CoapCmdService {

    @PostMapping("/cmd/period")
    String settingCmdPeriod(@RequestParam("id") String id,
                            @RequestParam("period") Integer period,
                            @RequestParam("unit") String unit);

    @ResponseBody
    @RequestMapping(value = "/cmd/para",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    String cmdPara(@RequestBody BoxPara boxPara);

}

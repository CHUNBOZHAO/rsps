package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.service.CoapCmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Coap服务设置
 */
@Controller
@RequestMapping("coap")
public class CoapSettingController {

    @Autowired
    private CoapCmdService coapCmdService;

    @RequestMapping("/cmd/period/setting")
    @ResponseBody
    public String settingCmdCoap(String boxId, Integer period, String unit) {
        String res = "failed";
        try {
            coapCmdService.settingCmdPeriod(boxId, period, unit);
            res = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}

package com.izhuixin.rsps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("guide")
@Controller
public class CustomGuideController {

    @RequestMapping(value = "/rsps", method = RequestMethod.GET)
    public String showRspsProfile() {
        return "custom/guide/rsps_profile";
    }

    @RequestMapping(value = "/logistics", method = RequestMethod.GET)
    public String showLogisticsProfile() {
        return "custom/guide/logistics_profile";
    }

    @RequestMapping(value = "/bloc", method = RequestMethod.GET)
    public String showBlocfile() {
        return "custom/guide/bloc_profile";
    }

    @RequestMapping(value = "profile/zx", method = RequestMethod.GET)
    public String showZxProfile() {
        return "custom/guide/zx_profile";
    }

}

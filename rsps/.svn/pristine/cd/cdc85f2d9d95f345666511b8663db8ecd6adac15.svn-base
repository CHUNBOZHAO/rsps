package com.izhuixin.rsps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

//    @RequestMapping("/")
//    public String index() {
//        return "redirect:/manager/";
//    }

    /**
     * 没有访问权限
     * @return
     */
    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "access_denied";
    }

}

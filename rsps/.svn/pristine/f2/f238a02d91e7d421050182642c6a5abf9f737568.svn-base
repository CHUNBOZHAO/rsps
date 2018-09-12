package com.izhuixin.authsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:17:02
 * ProjectName:Mirco-Service-Skeleton
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "order";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(String username,String password){
        return "hello";
    }
}

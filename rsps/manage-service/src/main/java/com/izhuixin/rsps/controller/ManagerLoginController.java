package com.izhuixin.rsps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ManagerLoginController {

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login")
    public String login(ModelMap modelMap, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            modelMap.put("error", "用户名或密码错误");
        }
        return "manager/login";
    }


    /**
     * 忘记密码
     * @return
     */
    @RequestMapping("/login/password/forget")
    public String forgetPassword() {
        return "manager/forget_pwd";
    }

    /**
     * App下载
     * @return
     */
    @RequestMapping("/login/app/download")
    public String downloadApp() {
        return "manager/download_app";
    }

//    /**
//     * 处理登录
//     * @return
//     */
//    @RequestMapping(value = "/handleLogin", method = RequestMethod.POST)
//    public String handleLogin() {
//        return "manager/index";
//    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("Authorization", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/manager/login";
    }

}

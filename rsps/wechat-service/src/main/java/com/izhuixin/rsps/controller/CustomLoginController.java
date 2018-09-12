package com.izhuixin.rsps.controller;

import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.WxConfigModel;
import com.izhuixin.rsps.service.feign.CustomInfoService;
import com.izhuixin.rsps.service.feign.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CustomLoginController {

    @Autowired
    private CustomInfoService customInfoService;

    @Autowired
    private WeChatService weChatService;

    @Value("${rsps.domain}")
    private String domain;

    @RequestMapping("/login")
    public String toLogin(ModelMap modelMap, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            modelMap.put("error", "用户名或密码错误");
        }

        // 微信jssdk参数配置
        StringBuilder sb = new StringBuilder();
        sb.append(domain);
        sb.append("/custom/login");
        if (error != null) {
            sb.append("?error=");
            sb.append(error);
        }
        WxConfigModel wxConfigModel = weChatService.getSignature(sb.toString());
        modelMap.put("wxConfigModel", wxConfigModel);

        return "custom/login";
    }

    @RequestMapping("/handleLogout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("Authorization", "");
        cookie.setMaxAge(0);
        cookie.setPath("/custom");
        response.addCookie(cookie);
        return "redirect:/custom/login";
    }

    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "custom/my/register";
    }

    /**
     * 用户注册处理
     * @param tel
     * @param password
     * @return
     */
    @RequestMapping(value = "/register/handleRegister", method = RequestMethod.POST)
    @ResponseBody
    public String handleRegister(String tel, String password) {
        boolean res = false;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "failed");
        boolean checkRes = customInfoService.checkTel(tel);
        if (!checkRes) { // 没有找到重复
            res = customInfoService.saveCustomInfo(tel, password);
        }
        if (res) {
            jsonObject.addProperty("result", "success");
        }
        return jsonObject.toString();
    }

    /**
     * 忘记密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/forget/password", method = RequestMethod.GET)
    public String forgetPwd(HttpServletRequest request) {
        return "custom/forget_pwd";
    }
}

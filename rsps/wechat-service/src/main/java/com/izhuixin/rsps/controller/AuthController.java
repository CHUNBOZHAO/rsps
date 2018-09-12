package com.izhuixin.rsps.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.izhuixin.rsps.common.CustomUserDetail;
import com.izhuixin.rsps.domain.CustomInfoDO;
import com.izhuixin.rsps.service.feign.CustomInfoService;
import com.izhuixin.rsps.service.feign.TokenService;
import feign.FeignException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

@Controller
public class AuthController {

    @Autowired
    public TokenService tokenService;

    @Autowired
    public CustomInfoService customInfoService;

    @RequestMapping(value = "/login/handle",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String doLogin(HttpServletRequest request,
                          HttpServletResponse response,
                          String username,
                          String password) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        String client = "wxApp";
        String secret = "wxApp";
        String clientauth = client+":"+secret;
        String encodedText="";
        try {
            byte[] textByte = clientauth.getBytes("UTF-8");
            encodedText = encoder.encodeToString(textByte);
        } catch (Exception e){
            e.printStackTrace();
        }
        String accessToken = "";
        try {
            accessToken = tokenService.getToken(client + " " + username, password, "password", "Basic " + encodedText);
        } catch (FeignException fe) {
            fe.printStackTrace();
            return "redirect:/custom/login?error=用户名或密码错误";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/custom/login";
        }
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(accessToken);

        if (jsonObject.has("access_token")) {
            Cookie cookie = new Cookie("Authorization", URLEncoder.encode("WX ".concat(jsonObject.get("access_token").getAsString()), "UTF-8"));
            cookie.setMaxAge(60 * 60 *2);
            cookie.setPath("/custom");
            response.addCookie(cookie);
        }

        CustomInfoDO customInfoDO = customInfoService.getCustomInfo(username);
        if (customInfoDO != null) {
            CustomUserDetail userDetail = new CustomUserDetail();
            String name = customInfoDO.getCustomName();
            if (StringUtils.isBlank(name)) {
                name = customInfoDO.getTel();
            }
            if (StringUtils.isBlank(name)) {
                name = customInfoDO.getCustomId();
            }
            if (StringUtils.isBlank(name)) {
                name = customInfoDO.getUserName();
            }
            if (StringUtils.isBlank(name)) {
                name = "客户";
            }

            userDetail.setName(name);
            userDetail.setUserId(customInfoDO.getCustomId());
            userDetail.setId(customInfoDO.getId());
            userDetail.setTel(customInfoDO.getTel());

            request.getSession().setAttribute("user", userDetail);
        }

        return "redirect:/custom/";
    }
}
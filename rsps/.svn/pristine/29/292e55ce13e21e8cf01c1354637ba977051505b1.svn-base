package com.izhuixin.rsps.controller;


import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.dao.PermissionInfo;
import com.izhuixin.rsps.common.dao.SysUserInfo;
import com.izhuixin.rsps.service.PermissionService;
import com.izhuixin.rsps.service.SysUserService;
import com.izhuixin.rsps.service.feign.TokenService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/login/handle",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String doLogin(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, String username, String password) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        String client = "webApp";
        String secret = "webApp";
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
            accessToken = tokenService.getToken(client+" "+username, password, "password", "Basic "+encodedText);
        } catch (FeignException fe) {
            fe.printStackTrace();
            return "redirect:/manager/login?error=用户名或密码错误";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/manager/login";
        }
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(accessToken);

        if (jsonObject.has("access_token")) {

            Cookie cookie = new Cookie("Authorization", URLEncoder.encode("M ".concat(jsonObject.get("access_token").getAsString()), "UTF-8"));
            cookie.setMaxAge(60 * 60 *2);
            cookie.setPath("/manager");
            response.addCookie(cookie);
        }



        SysUserInfo userInfo = sysUserService.getSysUser(username);

        ManagerUserDetail managerUserDetail = new ManagerUserDetail();
        managerUserDetail.setId(userInfo.getId());
        managerUserDetail.setCompanyName(userInfo.getNickname());
        managerUserDetail.setEntCode(userInfo.getEntCode());
        managerUserDetail.setEntId(userInfo.getEntId());
        managerUserDetail.setLevel(userInfo.getLevel());
        managerUserDetail.setNickName(userInfo.getNickname());
        managerUserDetail.setUserId(userInfo.getUserId());
        managerUserDetail.setUserType(userInfo.getUserType());
        managerUserDetail.setUserName(userInfo.getUserName());

        List<PermissionInfo> permissionInfos = permissionService.findPermissionByUserId(userInfo.getUserId());
        List<String> permissions = Lists.newArrayList();
        for (PermissionInfo permissionInfo : permissionInfos) {
            permissions.add(permissionInfo.getName());
        }
        managerUserDetail.setPermissions(permissions);

        request.getSession().setAttribute("user", managerUserDetail);

        return "redirect:/manager";
    }
}
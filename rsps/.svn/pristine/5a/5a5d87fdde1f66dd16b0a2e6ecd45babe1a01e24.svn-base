package com.izhuixin.rsps.controller;

import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.CustomUserDetail;
import com.izhuixin.rsps.common.ErrorCode;
import com.izhuixin.rsps.service.feign.CustomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("my")
public class CustomMyController {

    @Autowired
    private CustomInfoService customInfoService;

    @RequestMapping(value = "/showModifyPwd", method = RequestMethod.GET)
    public String showModifyPwd() {
        return "custom/my/password_modify";
    }

    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
    @ResponseBody
    public String modifyPwd(HttpServletRequest request,  String oldPassword, String newPassword) {
//        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        CustomUserDetail userDetail = (CustomUserDetail) request.getSession().getAttribute("user");

        JsonObject jsonObject = new JsonObject();
        boolean res = customInfoService.checkPwd(userDetail.getId(), oldPassword);
        if (res) {
            res = customInfoService.modifyPwd(userDetail.getId(), newPassword);
            if (res) {
                jsonObject.addProperty("result", "success");
                jsonObject.addProperty("msgCode", "0");
            } else {
                jsonObject.addProperty("result", "failed");
                jsonObject.addProperty("msgCode", ErrorCode.DB_ERROR.getIndex());
            }
        } else {
            jsonObject.addProperty("result", "failed");
            jsonObject.addProperty("msgCode", ErrorCode.USER_PWD_ERROR.getIndex());
        }
        return jsonObject.toString();
    }

}

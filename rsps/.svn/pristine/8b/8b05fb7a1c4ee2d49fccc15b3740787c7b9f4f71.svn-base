package com.izhuixin.rsps.controller;


import com.google.gson.Gson;
import com.izhuixin.rsps.common.constant.ErrorCode;
import com.izhuixin.rsps.common.vo.app.AppAckContent;
import com.izhuixin.rsps.common.vo.app.AppReqLoginUser;
import com.izhuixin.rsps.common.vo.app.AppResOperator;
import com.izhuixin.rsps.common.vo.app.JwtToken;
import com.izhuixin.rsps.service.AppRestService;
import com.izhuixin.rsps.service.EnterpriseService;
import com.izhuixin.rsps.service.feign.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("v2/")
public class AuthController {
    @Autowired
    TokenService tokenService;
    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private AppRestService appRestService;
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public AppResOperator doLogin(@RequestBody AppReqLoginUser appLoginUser){
        Base64.Encoder encoder = Base64.getEncoder();
        String client = "app";
        String secret = "app";
        String clientauth = client+":"+secret;
        String encodedText="";
        AppResOperator appResOperator;
        String retToken;
        try {
            byte[] textByte = clientauth.getBytes("UTF-8");
            encodedText = encoder.encodeToString(textByte);
        }catch (Exception e){
            e.printStackTrace();
        }

        String entCode = enterpriseService.checkUserName(appLoginUser.getUserName());
        if (StringUtils.isNotBlank(entCode)) {
            appLoginUser.setEntCode(entCode.concat("_"));
            appResOperator = appRestService.handleLogin(appLoginUser);
        } else {
            appResOperator = new AppResOperator();
            appResOperator.setStatus(ErrorCode.USER_NAME_NOT_EXIST.getIndex().toString());
            appResOperator.setMessage(ErrorCode.USER_NAME_NOT_EXIST.getDescr());
        }

        if("0".equals(appResOperator.getStatus())){
            retToken = tokenService.getToken(client+" "+appLoginUser.getUserName(), appLoginUser.getUserPwd(), "password", "Basic "+encodedText);
            if(retToken.contains("access_token")){
                JwtToken jwtToken = (new Gson()).fromJson(retToken, JwtToken.class);
                appResOperator.setAccessToken(jwtToken.getAccessToken());
            }
        }
        return appResOperator;
    }

    @ResponseBody
    @RequestMapping(value = "/access/invalid",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public AppAckContent doLogin(@RequestBody String any){
        AppAckContent appAckContent = new AppAckContent();
        appAckContent.setStatus(ErrorCode.USER_LOGIN_SESSION_INVALID.getIndex().toString());
        appAckContent.setMessage(ErrorCode.USER_LOGIN_SESSION_INVALID.getDescr());
        return appAckContent;
    }
}
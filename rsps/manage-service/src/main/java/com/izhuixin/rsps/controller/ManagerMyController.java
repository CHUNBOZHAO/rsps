package com.izhuixin.rsps.controller;

import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.constant.ErrorCode;
import com.izhuixin.rsps.common.vo.web.SysUserInfoVO;
import com.izhuixin.rsps.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("my")
public class ManagerMyController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/showModifyPwd")
    public String showModifyPwd() {
        return "manager/my/password_modify";
    }

    @RequestMapping("/modify/show")
    public String showModify(HttpServletRequest request, ModelMap modelMap) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        SysUserInfoVO vo = sysUserService.getUserInfoVOByUserId(userDetail.getUserId());
        modelMap.put("userInfo", vo);
        return "manager/my/my_modify";
    }

    @RequestMapping("/modifyPwd")
    @ResponseBody
    public String modifyPwd(HttpServletRequest request, String oldPassword, String newPassword) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        JsonObject jsonObject = new JsonObject();
        boolean res = sysUserService.checkPwd(userDetail.getId().intValue(), oldPassword);


        if (res) {
            res = sysUserService.updatePwd(userDetail.getId().intValue(), newPassword);
            if (res) {
                //新密码与原密码重复
                if(oldPassword.equals(newPassword)){
                    jsonObject.addProperty("result", "success");
                    jsonObject.addProperty("msgCode",111);
                }else {
                    jsonObject.addProperty("result", "success");
                    jsonObject.addProperty("msgCode", "0");
                }
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

package com.izhuixin.rsps.util;

import com.izhuixin.rsps.common.CustomUserDetail;
import com.izhuixin.rsps.service.feign.CustomInfoService;

import javax.servlet.http.HttpServletRequest;

public class SessionUserUtil {

    public static CustomUserDetail getUserDetail(CustomInfoService customInfoService, HttpServletRequest request) {
//        String customid = request.getHeader("Username");
//        CustomUserDetail custom = new CustomUserDetail();
//        if(null != customid) {
//            CustomInfoDO customInfo = customInfoService.getCustomInfo(customid);
//            if (null != customInfo) {
//                custom.setId(customInfo.getId());
//                custom.setUserId(customInfo.getCustomId());
//                custom.setName(customInfo.getCustomName());
//                custom.setTel(customInfo.getTel());
//                custom.setUserType(customInfo.getCustomType());
//            }
//        }
        return (CustomUserDetail) request.getSession().getAttribute("user");
    }
}

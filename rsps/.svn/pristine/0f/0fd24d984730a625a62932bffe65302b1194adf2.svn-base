package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.domain.automatic.CustomInfoDO;
import com.izhuixin.rsps.service.CustomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基础信息操作接口
 */
@RestController
@RequestMapping("v1/customData/")
public class CustomInfoController {

    @Autowired
    private CustomInfoService customInfoService;

    /**
     * 检测手机号是否被使用
     * @param tel
     * @return
     */
    @RequestMapping("/tel/check/{tel}")
    public boolean checkTel(@PathVariable String tel) {
        return customInfoService.checkTel(tel);
    }

    /**
     * 保存客户信息
     * @param tel
     * @param password
     * @return
     */
    @RequestMapping("/save")
    public boolean saveCustomInfo(String tel, String password) {
        return customInfoService.saveCustomInfo(tel, password);
    }

    /**
     * 获取客户信息
     * @param customId
     * @return
     */
    @RequestMapping("/customInfo/get/{customId}")
    public CustomInfoDO getCustomInfo(@PathVariable String customId) {
        return customInfoService.getCustomInfo(customId);
    }

    /**
     * 修改客户密码
     * @param id
     * @param newPassword
     * @return
     */
    @RequestMapping("/password/modify")
    public boolean modifyPwd(Long id, String newPassword) {
        return customInfoService.modifyPwd(id, newPassword);
    }

    /**
     * 检测客户旧密码正确性
     * @param id
     * @param oldPassword
     * @return
     */
    @RequestMapping("/password/check")
    public boolean checkPwd(Long id, String oldPassword) {
        return customInfoService.checkPwd(id, oldPassword);
    }

    /**
     * 通过barCode获取用户信息
     * @param barCode
     * @return
     */
    @RequestMapping("/customInfo/barcode/get/{barCode}")
    public String getCustomInfoByBarcode(@PathVariable String barCode) {
        return customInfoService.getCustomInfoByQcode(barCode);
    }

}

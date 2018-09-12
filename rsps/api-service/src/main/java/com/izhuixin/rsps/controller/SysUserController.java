package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.web.SysUserInfoVO;
import com.izhuixin.rsps.common.vo.web.SysUserTopoVO;
import com.izhuixin.rsps.domain.manual.SysUserInfo;
import com.izhuixin.rsps.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/sysUserData/userInfo")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/get/userId/{userId}")
    public SysUserInfo getUserInfoByUserId(@PathVariable String userId) {
        return sysUserService.getUserInfoByUserId(userId);
    }

    @RequestMapping("/get/site/userId/{userId}")
    public List<SysUserInfo> getSiteUserInfoByUserId(@PathVariable String userId) {
        return sysUserService.getSiteUserInfoByUserId(userId);
    }

    @RequestMapping("/vo/get/userId/{userId}")
    public SysUserInfoVO getUserInfoVOByUserId(@PathVariable String userId) {
        return sysUserService.getUserInfoVOByUserId(userId);
    }

    @RequestMapping("/get/userName/{userName}")
    public SysUserInfo getSysUser(@PathVariable String userName) {
        return sysUserService.getSysUser(userName);
    }

    @RequestMapping("/primaryUser/get/entId/{entId}")
    public SysUserInfo getPrimaryUser(@PathVariable String entId) {
        return sysUserService.getPrimaryUser(entId);
    }

    @RequestMapping("/ids/deepSysUser/userId/{userId}")
    public List<String> getDeepSysUserIds(@PathVariable String userId) {
        return sysUserService.getDeepSysUserIds(userId);
    }

    @RequestMapping("/infos/deepSysUser/userId/{userId}")
    public List<SysUserInfo> getDeepSysUserInfos(@PathVariable String userId) {
        return sysUserService.getDeepSysUserInfos(userId);
    }

    @RequestMapping("/vos/deepSysUser/userId/{userId}")
    public List<SysUserInfoVO> getDeepSysUserInfoVOs(@PathVariable String userId) {
        return sysUserService.getDeepSysUserInfoVOs(userId);
    }

    @RequestMapping("/topo/deepSysUser")
    public SysUserTopoVO getDeppSysUserTopoInfos(String userId, String nickName, String userName) {
        return sysUserService.getDeppSysUserTopoInfos(userId, nickName, userName);
    }

    @RequestMapping("/topo/deepEnterpriseSysUser")
    public SysUserTopoVO getDeppEnterpriseSysUserTopoInfos(String userId, String nickName, String userName) {
        return sysUserService.getDeppEnterpriseSysUserTopoInfos(userId, nickName, userName);
    }

    @RequestMapping("/save")
    public boolean saveUserInfo(@RequestBody SysUserInfoVO sysUserInfoVO) {
        return sysUserService.saveUserInfo(sysUserInfoVO);
    }

    @RequestMapping("/update")
    public boolean updateUserInfo(@RequestBody SysUserInfoVO sysUserInfoVO) {
        return sysUserService.updateUserInfo(sysUserInfoVO);
    }

    @RequestMapping("/password/reset/userId/{userId}")
    public boolean resetPwd(@PathVariable String userId) {
        return sysUserService.resetPwd(userId);
    }

    @RequestMapping("/delete/userId/{userId}")
    public boolean deleteUser(@PathVariable String userId) {
        return sysUserService.deleteUser(userId);
    }

    @RequestMapping("/userName/check")
    public boolean checkUserName(String userId, String userName) {
        return sysUserService.checkUserName(userId, userName);
    }

    @RequestMapping("/password/check")
    public boolean checkPwd(Integer id, String oldPwd) {
        return sysUserService.checkPwd(id, oldPwd);
    }

    @RequestMapping("/password/update")
    public boolean updatePwd(Integer id, String newPwd) {
        return sysUserService.updatePwd(id, newPwd);
    }

    /**
     * 根据userId和userType获取子用户信息
     */
    @RequestMapping("/getDeepSysUserInfoVOsByUserIdAndUserType")
    public List<SysUserInfoVO> getDeepSysUserInfoVOsByUserIdAndUserType(String userId,Integer userType){

        return sysUserService.getDeepSysUserInfoVOsByUserIdAndUserType(userId,userType);
    }
}

package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dao.SysUserInfo;
import com.izhuixin.rsps.common.vo.web.SysUserInfoVO;
import com.izhuixin.rsps.common.vo.web.SysUserTopoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "api-service")
@RequestMapping("/v1/sysUserData/userInfo")
public interface SysUserService {

    @RequestMapping("/get/userId/{userId}")
    SysUserInfo getUserInfoByUserId(@PathVariable("userId") String userId);

    @RequestMapping("/get/site/userId/{userId}")
    List<SysUserInfo> getSiteUserInfoByUserId(@PathVariable("userId") String userId);

    @RequestMapping("/vo/get/userId/{userId}")
    SysUserInfoVO getUserInfoVOByUserId(@PathVariable("userId") String userId);

    @RequestMapping(value = "/get/userName/{username}", method = RequestMethod.GET)
    SysUserInfo getSysUser(@PathVariable("username") String username);

    @RequestMapping("/primaryUser/get/entId/{entId}")
    SysUserInfo getPrimaryUser(@PathVariable("entId") String entId);

    @RequestMapping("/ids/deepSysUser/userId/{userId}")
    List<String> getDeepSysUserIds(@PathVariable("userId") String userId);

    @RequestMapping("/infos/deepSysUser/userId/{userId}")
    List<SysUserInfo> getDeepSysUserInfos(@PathVariable("userId") String userId);

    @RequestMapping("/vos/deepSysUser/userId/{userId}")
    List<SysUserInfoVO> getDeepSysUserInfoVOs(@PathVariable("userId") String userId);

    @RequestMapping("/topo/deepSysUser")
    SysUserTopoVO getDeppSysUserTopoInfos(@RequestParam("userId") String userId,
                                          @RequestParam("nickName") String nickName,
                                          @RequestParam("userName") String userName);

    @RequestMapping("/save")
    boolean saveUserInfo(@RequestBody SysUserInfoVO sysUserInfoVO);

    @RequestMapping("/update")
    boolean updateUserInfo(@RequestBody SysUserInfoVO sysUserInfoVO);

    @RequestMapping("/password/reset/userId/{userId}")
    boolean resetPwd(@PathVariable("userId") String userId);

    @RequestMapping("/delete/userId/{userId}")
    boolean deleteUser(@PathVariable("userId") String userId);

    @RequestMapping("/userName/check")
    boolean checkUserName(@RequestParam("userId") String userId, @RequestParam("userName") String userName);

    @RequestMapping("/password/check")
    boolean checkPwd(@RequestParam("id") Integer id,
                     @RequestParam("oldPwd") String oldPwd);

    @RequestMapping("/password/update")
    boolean updatePwd(@RequestParam("id") Integer id,
                      @RequestParam("newPwd") String newPwd);

    /**
     * 根据userId和userType获取子用户信息
     */
    @RequestMapping("/getDeepSysUserInfoVOsByUserIdAndUserType")
    List<SysUserInfoVO> getDeepSysUserInfoVOsByUserIdAndUserType(@RequestParam("userId") String userId,@RequestParam("userType") Integer userType);

    @RequestMapping("/topo/deepEnterpriseSysUser")
    SysUserTopoVO getDeppEnterpriseSysUserTopoInfos(@RequestParam("userId") String userId,
                                                    @RequestParam("nickName") String nickName,
                                                    @RequestParam("userName") String userName);
}

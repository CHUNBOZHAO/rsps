package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dao.EnterpriseInfo;
import com.izhuixin.rsps.common.vo.web.EnterpriseAndUserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "api-service")
@RequestMapping("v1/enterpriseData/")
public interface EnterpriseAndUserService {


    /**
     * 获取所有的企业信息
     * @return
     */
    @RequestMapping("/getAllEnterpriseAndUserInfo")
   List<EnterpriseAndUserInfo> getAllEnterpriseAndUserInfo();

 /**
  * 通过userId获取子企业和子企业的子企业
  * @param userId
  * @return
  */
   @RequestMapping("/getDeepEnterpriseAndUserByUserId")
   List<EnterpriseAndUserInfo> getDeepEnterpriseAndUserInfo(@RequestParam("userId") String userId);

    /**
     * 新增企业
     */
    @RequestMapping("/addEnterpriseAndUserInfo")
    boolean addEnterpriseAndUserInfo(@RequestBody EnterpriseAndUserInfo enterpriseAndUserInfo);

    /**
     * 校验企业名是否重名
     */
    @RequestMapping("/checkEntName")
    boolean checkEntName(@RequestParam("entName") String entName,@RequestParam("entId") String entId);

    /**
     * 检测企业编码有效性
     * @param entCode
     * @return
     */
    @RequestMapping("/checkEntCode")
    boolean checkEntCode2(@RequestParam("entCode") String entCode);


    /**
     * 更新企业用户
     */
    @RequestMapping("/updateEnterpriseAndUserInfo")
    boolean updateEnterpriseAndUser(@RequestBody EnterpriseAndUserInfo enterpriseAndUserInfo);
    /**
     * 删除企业
     */
    @RequestMapping("/deleteEnterpriseAndUserInfo")
    boolean deleteEnterpriseAndUser(@RequestParam("entId") String entId,@RequestParam("userId") String userId);

    /**
     * 根据entId来获取EnterpriseAndUserInfo
     */
    @RequestMapping("/getEnterpriseAndUserInfoByEntId")
    EnterpriseAndUserInfo getEnterpriseAndUserInfo(@RequestParam("entId") String entId);


    /**
     * 通过企业名获取企业信息
     */
    @RequestMapping("/getEnterpriseInfoByEntName")
    EnterpriseInfo getEnterpriseAndUserInfoByEntName(@RequestParam("entName") String entName);
}

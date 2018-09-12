package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.domain.automatic.Enterprise;
import com.izhuixin.rsps.domain.manual.EnterpriseAndUserInfo;
import com.izhuixin.rsps.domain.manual.EnterpriseInfo;
import com.izhuixin.rsps.domain.manual.SysUserInfo;

import java.util.List;

public interface EnterpriseService extends CrudService<Enterprise> {

    String checkUserName(String useName);

    Enterprise getEntByUserName(String userName);

//    String getEntCodeByPadId(String padId);

    String getEntIdByEntCode(String entCode);

    List<String> getEntCodes();

    String getEntCodeByBoxId(String boxId);

    boolean checkPwd(Integer id, String oldPwd);

    boolean updatePwd(Integer id, String newPwd);

    Byte getAccessWayByEntCode(String entCode);

    /**
     * 检测企业编码不重复
     * @param entCode
     * @return
     */
    boolean checkEntCode(String entCode);

    /**
     * 获取所有的企业信息
     */
    List<EnterpriseAndUserInfo> getAllEnterpriseAndUserInfo();
    /**
     * 根据userId获取所有的子用户信息
     */
    List<EnterpriseAndUserInfo> getDeepEnterpriseAndUserInfo(String userId);


    /**
     * 添加企业信息
     */
    boolean addEnterpriseAndUser(EnterpriseAndUserInfo EnterpriseAndUserInfo);


    /**
     * 检测企业名称不重复
     */
    boolean checkEntName(String entName,String entId);

    /**
     * 修改企业
     */
    boolean updateEnterpriseAndUserInfo(EnterpriseAndUserInfo enterpriseAndUserInfo);

    /**
     * 删除企业
     */
    boolean deleteEnterprise(String entId,String userId);

    /**
     * 根据ent_id查询EnterpriseAndUserInfo
     */
    EnterpriseAndUserInfo getEnterpriseAndUserInfoByEntId(String entId);


    /**
     * 根据企业名获取企业信息
     */
    EnterpriseInfo getEnterpriseInfoByEntName(String entName);
}

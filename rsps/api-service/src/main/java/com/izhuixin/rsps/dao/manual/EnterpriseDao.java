package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.EnterpriseAndUserInfo;
import com.izhuixin.rsps.domain.manual.EnterpriseInfo;
import com.izhuixin.rsps.domain.manual.SysUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * 企业信息DAO
 */
@Repository
public interface EnterpriseDao {

    /**
     * 通过PadID获取绑定的企业信息
     * @param padId
     * @return
     */
    EnterpriseInfo getEnterpriseByPadId(@Param("padId") String padId);


    /**
     * 通过有效的访问编码获取企业信息
     * @param entCode
     * @return
     */
    EnterpriseInfo getEnterpriseByEntCode(@Param("entCode") String entCode);

    /**
     * 通过包装箱ID
     * @param boxId
     * @return
     */
    String getEntCodeByBoxId(@Param("boxId") String boxId);

    /***
     * 检测用户密码
     * @param id
     * @param oldPwd
     * @return
     */
    Integer checkPwd(@Param("id") Integer id, @Param("oldPwd") String oldPwd);

    /***
     * 修改用户密码
     * @param id
     * @param pwd
     * @return
     */
    Integer updatePwd(@Param("id") Integer id, @Param("pwd") String pwd);


    /**
     * 检测企业编码
     * @param entCode
     * @return
     */
    Integer countByEntCode(@Param("entCode") String entCode);

    /**
     * 通过entId获取entCode
     * @param entId
     * @return
     */
    String getEntCodeByEntId(@Param("entId") String entId);


    /**
     *获取所有的企业用户信息
     */
    List<EnterpriseAndUserInfo> getAllEnterpriseAndUserInfo();

    /**
     * 根据userId获取子企业
     */
    List<EnterpriseAndUserInfo> getAllEnterpriseAndUserInfoByUserId(String userId);


    /**
     * 添加企业
     */
    boolean addEnterprise(EnterpriseInfo enterpriseInfo);

    /**
     * 添加企业用户
     */
    boolean addEnterpriseUser(SysUserInfo sysUserInfo);

    /**
     * 获取最后一个企业的id
     */
    String getLastEnt_id();

    /**
     * 检测企业名称不重复
     */
    Integer checkEntName(String entName);

    /**
     * 检测编辑时企业名称不重复
     */
    Integer checkEntNameForEdit(String entName,String entId);

    /**
     * 修改企业
     */
    boolean updateEnterprise(EnterpriseInfo enterpriseInfo);

    /**
     *修改企业用户
     */
    boolean updateEnterpriseUser(SysUserInfo sysUserInfo);


    /**
     * 根据企业id删除企业
     */
    boolean deleteEnterprise(String entId);

    /**
     * 根据企业id删除企业用户
     */
    boolean deleteEnterpriseUser(String entId);

    /**
     * 根据企业id删除用户权限
     */
    boolean deleteUserPermission(String entId);

    /**
     * 根据ent_id获取一个EnterpriseAndUserInfo对象
     */
    EnterpriseAndUserInfo getEnterpriseAndUserInfoByEntId(String entId);


    /**
     * 创建企业业务表
     */
    void createEnterpriseTable(String entCode);

    /**
     * 根据entCode删除企业业务表
     */
    void dropEnterpriseTable(String entCode);

    /**
     * 根据企业id获取企业信息
     */
    EnterpriseInfo getEnterpriseInfoByEntId(String entId);

    /**
     * 根据企业名获取企业信息
     */
    EnterpriseInfo getEnterpriseInfoByEntName(String entName);
}

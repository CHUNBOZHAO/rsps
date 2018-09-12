package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.PermissionInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionDao {

    /**
     * 获取所有权限
     * @return
     */
    List<PermissionInfo> findAllPermissions();


    /**
     * 获取用户的权限信息
     * @param userId
     * @return
     */
    List<PermissionInfo> findPermissionByUserId(@Param("userId") String userId);


    /**
     * 删除用户权限
     * @param userId
     * @return
     */
    Integer deleteUserPermission(@Param("userId") String userId);


    /**
     * 新增用户权限
     * @param userId
     * @param permissionId
     * @return
     */
    Integer saveUserPermission(@Param("permissionId") String permissionId, @Param("userId") String userId);


    /**
     * 获取权限ID
     * @param permissionName
     * @return
     */
    String getPermissionId(@Param("permissionName") String permissionName);


    /**
     * 删除用户对应权限
     * @param userId
     * @return
     */
    Integer deleteUserSpecifiedPermission(@Param("userId") String userId, @Param("permissionId") String permissionId);
}

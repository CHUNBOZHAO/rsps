package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.vo.web.PermissionInfoVO;
import com.izhuixin.rsps.domain.manual.PermissionInfo;

import java.util.List;

public interface PermissionService {
    List<PermissionInfo> findAllPermissions();

    List<PermissionInfo> findPermissionByUserId(String userId);

    List<PermissionInfoVO> getPermissionInfoByUser(String curUserId, String targetUserId);

    /**
     * 根据targetId获取PermissionInfoVO
     * @param targetUserId
     * @return
     */
    List<PermissionInfoVO> getPermissionInfoByUserId(String targetUserId);

    boolean settingPermission(String targetUserId, String[] permissionIds);
}

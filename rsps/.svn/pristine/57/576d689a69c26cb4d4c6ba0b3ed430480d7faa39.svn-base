package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.web.PermissionInfoVO;
import com.izhuixin.rsps.domain.manual.PermissionInfo;
import com.izhuixin.rsps.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/permissionData/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/permissions/query")
    public List<PermissionInfo> findAllPermissions() {
        return permissionService.findAllPermissions();
    }

    @RequestMapping("/permissionsVO/queryByTargetId")
    public List<PermissionInfoVO> findAllPermissionsVOByTargetId(String targetId){

        return permissionService.getPermissionInfoByUserId(targetId);
    }

    @RequestMapping("/permissions/query/userId/{userId}")
    public List<PermissionInfo> findPermissionByUserId(@PathVariable String userId) {
        return permissionService.findPermissionByUserId(userId);
    }

    @RequestMapping("/permissions/query/userId/{curUserId}/targetUserId/{targetUserId}")
    public List<PermissionInfoVO> getPermissionInfoByUser(@PathVariable String curUserId, @PathVariable String targetUserId) {
        return permissionService.getPermissionInfoByUser(curUserId, targetUserId);
    }

    @RequestMapping("/setting")
    public boolean settingPermission(String targetUserId, String[] permissionIds) {
        return permissionService.settingPermission(targetUserId, permissionIds);
    }
}

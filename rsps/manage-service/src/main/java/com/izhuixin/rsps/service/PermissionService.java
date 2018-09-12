package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dao.PermissionInfo;
import com.izhuixin.rsps.common.vo.web.PermissionInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("api-service")
@RequestMapping("v1/permissionData/permission")
public interface PermissionService {

    @RequestMapping("/permissions/query")
    List<PermissionInfo> findAllPermissions();

    @RequestMapping("/permissionsVO/queryByTargetId")
    List<PermissionInfoVO> findAllPermissionsVOByTargetId(@RequestParam("targetId") String targetId);

    @GetMapping("/permissions/query/userId/{userId}")
    List<PermissionInfo> findPermissionByUserId(@PathVariable("userId") String userId);

    @RequestMapping("/permissions/query/userId/{curUserId}/targetUserId/{targetUserId}")
    List<PermissionInfoVO> getPermissionInfoByUser(@PathVariable("curUserId") String curUserId,
                                                   @PathVariable("targetUserId") String targetUserId);

    @RequestMapping("/setting")
    boolean settingPermission(@RequestParam("targetUserId") String targetUserId,
                              @RequestParam("permissionIds") String[] permissionIds);
}

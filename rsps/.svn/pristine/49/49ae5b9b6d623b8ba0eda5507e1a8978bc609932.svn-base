package com.izhuixin.authsample.service.impl;


import com.izhuixin.authsample.entity.SysPermissionEntity;
import com.izhuixin.authsample.repository.SysPermissionRepository;
import com.izhuixin.authsample.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:12:38
 * ProjectName:Mirco-Service-Skeleton
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @Override
    public List<SysPermissionEntity> getPermissionsByUserId(String roleId) {
        return sysPermissionRepository.getPermissionsByUserId(roleId);
    }
}

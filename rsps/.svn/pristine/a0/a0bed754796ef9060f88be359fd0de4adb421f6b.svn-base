package com.izhuixin.authsample.service.impl;


import com.izhuixin.authsample.entity.SysUserEntity;
import com.izhuixin.authsample.repository.SysUserRepository;
import com.izhuixin.authsample.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:15:13
 * ProjectName:Mirco-Service-Skeleton
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUserEntity findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}

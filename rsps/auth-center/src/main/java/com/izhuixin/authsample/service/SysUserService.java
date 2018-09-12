package com.izhuixin.authsample.service;


import com.izhuixin.authsample.entity.SysUserEntity;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:15:12
 * ProjectName:Mirco-Service-Skeleton
 */
public interface SysUserService {
    SysUserEntity findByUsername(String username);
}

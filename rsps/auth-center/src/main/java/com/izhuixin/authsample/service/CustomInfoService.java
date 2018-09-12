package com.izhuixin.authsample.service;


import com.izhuixin.authsample.entity.CustomInfoEntity;
import com.izhuixin.authsample.entity.OperatorInfoEntity;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:15:12
 * ProjectName:Mirco-Service-Skeleton
 */
public interface CustomInfoService {
    CustomInfoEntity findByCustomid(String username);
}

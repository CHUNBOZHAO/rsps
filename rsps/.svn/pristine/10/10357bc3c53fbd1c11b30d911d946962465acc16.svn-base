package com.izhuixin.authsample.service.impl;


import com.izhuixin.authsample.entity.CustomInfoEntity;
import com.izhuixin.authsample.repository.CustomInfoRepository;
import com.izhuixin.authsample.service.CustomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:15:13
 * ProjectName:Mirco-Service-Skeleton
 */
@Service
public class CustomInfoServiceImpl implements CustomInfoService {
    @Autowired
    private CustomInfoRepository operatorInfoRepository;

    @Override
    public CustomInfoEntity findByCustomid(String username) {
        return operatorInfoRepository.findByCustomid(username);
    }
}

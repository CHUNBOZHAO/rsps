package com.izhuixin.rsps.service.impl;


import com.izhuixin.rsps.dao.BoxBaseDao;
import com.izhuixin.rsps.service.BoxBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoxBaseServiceImp implements BoxBaseService {
    @Autowired
    private BoxBaseDao boxBaseDao;

    @Override
    public boolean updateCommunicateNum(String uuid) {
        return boxBaseDao.updateCommunicateNum(uuid);
    }

}

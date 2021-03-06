package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.ApiService;
import com.izhuixin.rsps.dao.manual.BoxDetailDao;
import com.izhuixin.rsps.domain.manual.BoxDetailInfo;
import com.izhuixin.rsps.service.BoxDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoxDetailServiceImpl implements BoxDetailService {

    private Logger logger = LoggerFactory.getLogger(ApiService.class);
    @Autowired
    private BoxDetailDao boxDetailDao;


    @Override
    public boolean saveBoxDetail(BoxDetailInfo boxDetailInfo) {
        boolean flag = false;

        try{
            if(boxDetailDao.checkBoxId(boxDetailInfo.getUuid())<1){
                //保存
                boxDetailDao.saveBoxDetail(boxDetailInfo);
            }else {
                //更新
                boxDetailDao.updateBoxDetail(boxDetailInfo);
            }

            flag = true;
        }catch (Exception e){
            logger.info("保存失败"+boxDetailInfo.getBoxId(),e);
        }
        return flag;
    }

    @Override
    public BoxDetailInfo getBoxDetailInfoByRfid(String uuid) {

        BoxDetailInfo boxDetailInfo = null;
        try{
            boxDetailInfo = boxDetailDao.getBoxDetailInfoByBoxId(uuid);

        }catch (Exception e){
            logger.info("通过rfid获取包装箱详细信息失败rfid:"+uuid,e);
        }

        return boxDetailInfo;
    }
}

package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.dao.BaseStationDao;
import com.izhuixin.rsps.model.BoxInfo;
import com.izhuixin.rsps.model.BoxLocationRecordInfo;
import com.izhuixin.rsps.model.CoapModel.BaseStation;
import com.izhuixin.rsps.service.BaseStationService;
import com.izhuixin.rsps.service.feign.FeignService;
import com.izhuixin.rsps.util.LocationCache;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseStationServiceImpl implements BaseStationService {

    @Autowired
    private BaseStationDao bsDao;

    @Autowired
    FeignService feignService;

    /**
     * 获取基站信息
     * @param ID
     * @return
     */
    @Override
    public BaseStation getBaseStation(String ID) {
        return bsDao.getBaseStation(ID);
    }

    @Override
    public void updateBoxLocation(String boxid,Double lng, Double lat){
        if (lng.longValue() != Double.MIN_VALUE && lat != Double.MIN_VALUE) {
            String rfid = feignService.getBoxIdByBle(boxid);
            if(null == rfid){
                return;
            }
            BoxInfo boxInfo = new BoxInfo();
            boxInfo.setRfid(rfid);
            boxInfo.setDuAddress("");
            boxInfo.setDuCoordType((byte)1);
            boxInfo.setDuLatitude(lat);
            boxInfo.setDuLongitude(lng);
            boxInfo.setUpdateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            String enCode = feignService.getEntCodeByBoxId(rfid);
            feignService.updateBoxInfoByRfid(boxInfo, enCode+"_");

            BoxLocationRecordInfo recordInfo = new BoxLocationRecordInfo();
            recordInfo.setRfid(rfid);
            //recordInfo.setOrderId(itemBoxInfo.getOrderId());
            recordInfo.setDuAddress("");
            recordInfo.setDuCoordType((byte)1);
            recordInfo.setDuLatitude(lat);
            recordInfo.setDuLongitude(lng);
            recordInfo.setCreateTime(new Date());
            recordInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

            //boolean checkLocationRes = LocationCache.checkRepeat(itemBoxInfo.getOrderId(), itemBoxInfo.getRfid(), appReqPushOpLocation.getLatitude(), appReqPushOpLocation.getLongitude());


            feignService.saveBoxLocation(recordInfo, enCode+"_");
        }
    }
}

package com.izhuixin.rsps.service.impl;

import com.google.common.collect.Lists;
import com.izhuixin.rsps.model.*;
import com.izhuixin.rsps.service.AppLoctionService;
import com.izhuixin.rsps.service.feign.FeignService;
import com.izhuixin.rsps.util.LocationCache;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppLoctionServiceImpl implements AppLoctionService {

    @Autowired
    FeignService feignService;

    private static Logger logger = LoggerFactory.getLogger(AppLoctionServiceImpl.class);
    /**
     * 推送位置信息
     * @param appReqPushOpLocation
     * @return
     */
    @Override
    public String pushOperatorLocation(final AppReqPushOpLocation appReqPushOpLocation){

        boolean res = false;

        try {


            String entId = feignService.getEntIdByEntCode(appReqPushOpLocation.getEntCode().replace("_",""));
            boolean checkRes = feignService.checkExist(appReqPushOpLocation.getOperatorName(), entId);


            OperatorLocation operatorLocation = new OperatorLocation();
            operatorLocation.setCoordType(appReqPushOpLocation.getCoordType());
            operatorLocation.setEntId(entId);
            operatorLocation.setLatitude(appReqPushOpLocation.getLatitude());
            operatorLocation.setLongitude(appReqPushOpLocation.getLongitude());
            operatorLocation.setModifyTime(new Date());
            operatorLocation.setOperatorId(appReqPushOpLocation.getOperatorId());
            operatorLocation.setOperatorName(appReqPushOpLocation.getOperatorName());
            operatorLocation.setAddress(appReqPushOpLocation.getAddress());
            if (checkRes) { // 更新
                res = feignService.updateLocation(operatorLocation);
            } else {
                operatorLocation.setCreateTime(new Date());
                res = feignService.saveLocation(operatorLocation);
            }

            // 查询该操作人员名下"运输"、"回收"状态的包装箱信息
            List<Byte> boxStatus = Lists.newArrayList();
            boxStatus.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
            boxStatus.add(BoxStatus.RECYCLE.getIndex().byteValue());

            System.out.println("boxStatus:  "+boxStatus.get(0)+"  "+boxStatus.get(1)+" "+boxStatus.size());


            List<BoxInfoVO> boxInfos = feignService.queryBoxesByOperatorId(boxStatus, appReqPushOpLocation.getOperatorId(), appReqPushOpLocation.getEntCode().concat("_"));
            System.out.println("boxInfos:  "+boxInfos.size());

            BoxInfo boxInfo = null;
            for (BoxInfoVO itemBoxInfo : boxInfos) {

                if (appReqPushOpLocation.getLatitude().longValue() != Double.MIN_VALUE && appReqPushOpLocation.getLongitude() != Double.MIN_VALUE) {

                    boxInfo = new BoxInfo();
                    boxInfo.setRfid(itemBoxInfo.getRfid());
                    boxInfo.setDuAddress(appReqPushOpLocation.getAddress());
                    boxInfo.setDuCoordType(appReqPushOpLocation.getCoordType());
                    boxInfo.setDuLatitude(appReqPushOpLocation.getLatitude());
                    boxInfo.setDuLongitude(appReqPushOpLocation.getLongitude());
                    boxInfo.setUpdateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                    feignService.updateBoxInfoByRfid(boxInfo, appReqPushOpLocation.getEntCode().concat("_"));

                    BoxLocationRecordInfo recordInfo = new BoxLocationRecordInfo();
                    recordInfo.setRfid(itemBoxInfo.getRfid());
                    recordInfo.setOrderId(itemBoxInfo.getOrderId());
                    recordInfo.setDuAddress(appReqPushOpLocation.getAddress());
                    recordInfo.setDuCoordType(appReqPushOpLocation.getCoordType());
                    recordInfo.setDuLatitude(appReqPushOpLocation.getLatitude());
                    recordInfo.setDuLongitude(appReqPushOpLocation.getLongitude());
                    recordInfo.setCreateTime(new Date());
                    recordInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

                    boolean checkLocationRes = LocationCache.checkRepeat(itemBoxInfo.getOrderId(), itemBoxInfo.getRfid(), appReqPushOpLocation.getLatitude(), appReqPushOpLocation.getLongitude());

                    System.out.println("checkLocationRes:  "+checkLocationRes);
                    if (!checkLocationRes) { // 重复数据不保存

                        System.out.println("appReqPushOpLocation: "+appReqPushOpLocation);
                        feignService.saveBoxLocation(recordInfo, appReqPushOpLocation.getEntCode().concat("_"));
                    }
                } else {

                }
            }

        } catch (Exception e) {
            logger.error(e.toString());
        }
        return "";
    }
}

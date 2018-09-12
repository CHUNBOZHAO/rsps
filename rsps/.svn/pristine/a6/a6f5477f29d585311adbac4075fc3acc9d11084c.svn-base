package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.constant.OperatorType;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.object.Pair;
import com.izhuixin.rsps.common.util.FinalPositionUtils;
import com.izhuixin.rsps.dao.manual.OperatorInfoDao;
import com.izhuixin.rsps.dao.manual.OperatorLocationDao;
import com.izhuixin.rsps.domain.automatic.OperatorLocation;
import com.izhuixin.rsps.domain.automatic.OrderDO;
import com.izhuixin.rsps.domain.manual.OperatorInfo;
import com.izhuixin.rsps.service.*;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorLocationServiceImpl extends AbstractCrudService<OperatorLocation> implements OperatorLocationService {


    @Autowired
    private OrderService orderService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private OperatorInfoService operatorInfoService;

    @Autowired
    private OperatorInfoDao operatorInfoDao;

    @Autowired
    private OperatorLocationDao operatorLocationDao;


    /**
     * 检查配送员是否在线，通过位置更新时间进行判断
     * @param userName
     * @param operatorId
     * @param intervalTime 离线判断依据时长(秒)
     * @return
     */
    @Override
    public boolean checkOnline(String userName, String operatorId, Integer intervalTime) {
        boolean res = false;
        try {
            DateTime dt = DateTime.now();
            DateTime startDt = dt.minusSeconds(intervalTime);
            DateTime endDt = dt.plusSeconds(intervalTime);

            Integer resInt = operatorLocationDao.checkOnline(userName, operatorId, startDt.toString("yyyy-MM-dd HH:mm:ss"), endDt.toString("yyyy-MM-dd HH:mm:ss"));
            if (resInt > 0) {
                res = true;
            }
        } catch (Exception e) {
            logger.error("检查配送员是否在线出现异常", e);
        }
        return res;
    }

    /**
     * 保存操作人员位置信息
     * @param operatorLocation
     * @return
     */
    @Override
    public boolean saveLocation(OperatorLocation operatorLocation) {
        boolean res = false;
        String operatorName = "";
        try {
            operatorName = operatorLocation.getOperatorName();
            save(operatorLocation);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("保存操作人员(%s)位置信息出现异常", operatorName), e);
            throw e;
        }
        return res;
    }

    /**
     * 更新操作人员位置信息
     * @param operatorLocation
     * @return
     */
    @Override
    public boolean updateLocation(OperatorLocation operatorLocation) {
        boolean res = false;
        String operatorName = "";
        try {
            operatorName = operatorLocation.getOperatorName();
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("operator_name", operatorLocation.getOperatorName()).andFieldEqualTo("ent_id", operatorLocation.getEntId());
            update(operatorLocation, fe);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("更新操作人员(%s)位置信息出现异常", operatorName), e);
            throw e;
        }
        return res;
    }

    /**
     * 检测记录是否存在
     * @param operatorName
     * @param entId
     * @return
     */
    @Override
    public boolean checkExist(String operatorName, String entId) {
        boolean res = false;
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("operator_name", operatorName).andFieldEqualTo("ent_id", entId);
        long count = count(fe);
        if (count > 0) {
            res = true;
        }
        return res;
    }

    /**
     * 获取订单地址附近快递人员
     * @param orderId
     * @param entCode
     * @param addressType -- 0:默认获取企业第一个人 1：寄件人地址匹配；2：收件人地址匹配
     * @return
     */
    @Override
    public OperatorInfo getNearbyOperator(String orderId, String entCode, byte addressType) {
        OperatorInfo operatorInfo = null;
        if (addressType == 0) {  //
            List<OperatorInfo> operatorInfos = operatorInfoService.getOperatorInfos(entCode);
            if (!operatorInfos.isEmpty()) {
                operatorInfo = operatorInfos.get(0);
            }
        } else {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("order_id", orderId);
            Optional<OrderDO> orderDOOptional = orderService.get(fe);
            if (orderDOOptional.isPresent()) {
                OrderDO orderDO = orderDOOptional.get();
                String address = "";
                if (addressType == 1) {
                    address = orderDO.getSenderAddress().replaceAll(" ","");
                } else if (addressType == 2) {
                    address = orderDO.getReceiverAddress().replaceAll(" ","");
                }
                JsonObject jsonObject = positionService.getPositionByAddr(address, "");
                if (jsonObject != null) {
                    Double lng = jsonObject.get("lng").getAsDouble();
                    Double lat = jsonObject.get("lat").getAsDouble();

                    String entId = enterpriseService.getEntIdByEntCode(entCode.replace("_",""));

                    Pair<String, Double> pair = this.assignOrder(lng, lat, entId);
                    if (StringUtils.isNotBlank(pair.getFirst())) {
                        String[] opArray = pair.getFirst().split("_");
                        if (opArray.length > 0) {
                            String operatorName = pair.getFirst();
                            operatorInfo = operatorInfoDao.getOperatorInfoByUserName(operatorName, OperatorType.DRIVER.getIndex().byteValue(), entCode);
                        }
                    }
                }
            }
        }

        return operatorInfo;
    }

    /**
     * 根据距离派货
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public Pair<String, Double> assignOrder(Double longitude, Double latitude, String entId) {
        Double factor = (10000 / 1000) * 0.01;
        FilterExample fe = new FilterExample();
        FilterExample.Criteria criteria = fe.createCriteria();
        criteria.andFieldBetween("longitude", longitude - factor, longitude + factor).andFieldBetween("latitude", latitude - factor, longitude + factor);
        DateTime confineDt = new DateTime();
        confineDt = confineDt.minusMinutes(30);
        criteria.andFieldGreaterThan("modify_time", confineDt.toDate());
        if (StringUtils.isNotBlank(entId)) {
            criteria.andFieldEqualTo("ent_id", entId);
        }
        List<OperatorLocation> locations = getList(fe);
        Pair<String, Double> pair = Pair.of("", 0d);
        Double distance = 0d;
        for (OperatorLocation location : locations) {
            distance = FinalPositionUtils.GetDistance(latitude, longitude, location.getLatitude(), location.getLongitude());
            if (StringUtils.isBlank(pair.getFirst())) {
                pair.setFirst(location.getOperatorName());
                pair.setSecond(distance);
            } else {
                if (pair.getSecond() - distance > 0) {
                    pair.setFirst(location.getOperatorName());
                    pair.setSecond(distance);
                }
            }
            logger.info("name:" + location.getOperatorName() + "distance:" + distance);
        }
        logger.info("name:" + pair.getFirst() + " <---> distance:" + pair.getSecond());
        return pair;
    }

}

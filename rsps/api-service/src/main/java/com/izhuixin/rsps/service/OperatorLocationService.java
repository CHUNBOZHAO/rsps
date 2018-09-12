package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.common.object.Pair;
import com.izhuixin.rsps.domain.automatic.OperatorLocation;
import com.izhuixin.rsps.domain.manual.OperatorInfo;

public interface OperatorLocationService extends CrudService<OperatorLocation> {
    boolean checkOnline(String userName, String operatorId, Integer intervalTime);

    boolean saveLocation(OperatorLocation operatorLocation);

    boolean updateLocation(OperatorLocation operatorLocation);

    boolean checkExist(String operatorName, String entId);

    OperatorInfo getNearbyOperator(String orderId, String entCode, byte addressType);

    Pair<String, Double> assignOrder(Double longitude, Double latitude, String entId);
}

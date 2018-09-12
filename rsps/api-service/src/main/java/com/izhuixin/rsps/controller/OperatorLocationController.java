package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.object.Pair;
import com.izhuixin.rsps.domain.automatic.OperatorLocation;
import com.izhuixin.rsps.domain.manual.OperatorInfo;
import com.izhuixin.rsps.service.OperatorLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/operatorData/location")
public class OperatorLocationController {

    @Autowired
    private OperatorLocationService operatorLocationService;

    @RequestMapping("/save")
    public boolean saveLocation(@RequestBody OperatorLocation operatorLocation) {
        return operatorLocationService.saveLocation(operatorLocation);
    }

    @RequestMapping("/update")
    public boolean updateLocation(@RequestBody OperatorLocation operatorLocation) {
        return operatorLocationService.updateLocation(operatorLocation);
    }

    @RequestMapping("/exist/check/{operatorName}/{entId}")
    public boolean checkExist(@PathVariable String operatorName, @PathVariable String entId) {
        return operatorLocationService.checkExist(operatorName, entId);
    }

    @RequestMapping("/operator/nearby/get")
    public OperatorInfo getNearbyOperator(String orderId, String entCode, byte addressType) {
        return operatorLocationService.getNearbyOperator(orderId, entCode, addressType);
    }

    @RequestMapping("/order/assign")
    public Pair<String, Double> assignOrder(Double longitude, Double latitude, String entId) {
        return operatorLocationService.assignOrder(longitude, latitude, entId);
    }

}

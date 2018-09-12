package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import com.izhuixin.rsps.domain.manual.OrderDriverInfo;
import com.izhuixin.rsps.service.OrderDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/orderData/driver")
public class OrderDriverController {

    @Autowired
    private OrderDriverService orderDriverService;

    @RequestMapping("/next/get")
    public OrderDriverInfo queryNextDriver(String driverId, String orderId, String entCode) {
        return orderDriverService.queryNextDriver(driverId, orderId, entCode);
    }

    @RequestMapping("/count")
    public Integer countOrderDriver(String orderId, String driverId, String entCode) {
        return orderDriverService.countOrderDriver(orderId, driverId, entCode);
    }

    @RequestMapping("/save")
    public boolean saveOrderDriver(@RequestBody OrderDriverInfo orderDriverInfo, String entCode) {
        return orderDriverService.saveOrderDriver(orderDriverInfo, entCode);
    }

    @RequestMapping("/update")
    public boolean updateByOrderIdAndDriverId(@RequestBody OrderDriverInfo orderDriverInfo, String entCode) {
        return orderDriverService.updateByOrderIdAndDriverId(orderDriverInfo, entCode);
    }

    @RequestMapping("/flowRecord/get/{orderId}")
    public BoxFlowRecordVO getFlowRecord(String orderId) {
        return orderDriverService.getFlowRecord(orderId);
    }
}

package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.domain.manual.OrderBoxInfo;
import com.izhuixin.rsps.service.OrderBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单相关包装箱信息接口
 */
@RestController
@RequestMapping("v1/orderData/box")
public class OrderBoxController{

    @Autowired
    private OrderBoxService orderBoxService;

    @RequestMapping("/get")
    public OrderBoxInfo getBoxInfo(String orderId, String rfid, String entCode) {
        return orderBoxService.getBoxInfo(orderId, rfid, entCode);
    }

    @RequestMapping("/count/rfid/barCode")
    public Integer countOrderBoxByRfidAndBarCode(String rfid, String barcode, String entCode) {
        return orderBoxService.countOrderBoxByRfidAndBarCode(rfid, barcode, entCode);
    }

    @RequestMapping("/update")
    public boolean updateOrderBox(@RequestBody OrderBoxInfo orderBoxInfo, String entCode) {
        return orderBoxService.updateOrderBox(orderBoxInfo, entCode);
    }

    @RequestMapping("/save")
    public boolean saveOrderBox(@RequestBody OrderBoxInfo orderBoxInfo, String entCode) {
        return orderBoxService.saveOrderBox(orderBoxInfo, entCode);
    }

    /**
     * 更新包装箱责任人（施必达提供）
     * @param orderId
     * @param operatorNo
     * @return
     */
    @RequestMapping("/operator/box/update")
    public boolean updateBoxOperator(String orderId, String operatorNo, String entCode) {
        return orderBoxService.updateBoxOperator(orderId, operatorNo, entCode);
    }
}

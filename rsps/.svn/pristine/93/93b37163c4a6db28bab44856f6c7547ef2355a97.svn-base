package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.service.LineTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/lineData/")
public class LineTransferController {

    @Autowired
    private LineTransferService lineTransferService;

    /**
     * 检测包装箱送达状态
     * @param boxId
     * @param operatorId
     * @param entCode
     * @return
     */
    @RequestMapping("/deliveryStatus/check/{boxId}/{operatorId}/{entCode}")
    public String checkDeliveryStatus(@PathVariable String boxId, @PathVariable String operatorId, @PathVariable String entCode) {
        return lineTransferService.checkDeliveryStatus(boxId, operatorId, entCode);
    }
}

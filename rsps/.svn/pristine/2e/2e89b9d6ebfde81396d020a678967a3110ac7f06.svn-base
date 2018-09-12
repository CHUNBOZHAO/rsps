package com.izhuixin.rsps.controller;

import com.google.gson.JsonObject;
import com.izhuixin.rsps.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/positionData/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping("/get/address")
    public JsonObject getPositionByAddr(String address, String city) {
        return positionService.getPositionByAddr(address, city);
    }
}

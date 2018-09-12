package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.model.CoapModel.BaseStation;
import com.izhuixin.rsps.service.BaseStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private BaseStationService baseStationService;


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){

        return "hello Spring boot";
    }

    @RequestMapping(value = "/getBaseStationById",method = RequestMethod.POST)
    public BaseStation getBaseStationById(@RequestParam String id){

        BaseStation baseStation = baseStationService.getBaseStation(id);
        return baseStation;
    }

}

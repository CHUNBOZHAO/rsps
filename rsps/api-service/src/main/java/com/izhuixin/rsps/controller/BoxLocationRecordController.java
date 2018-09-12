package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.domain.manual.BoxLocationRecordInfo;
import com.izhuixin.rsps.service.BoxLocationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/boxData/location")
public class BoxLocationRecordController {

    @Autowired
    private BoxLocationRecordService boxLocationRecordService;
    @RequestMapping("/record/track")
    String queryBoxTrack(String boxId, String orderId){
        return boxLocationRecordService.queryBoxTrack(boxId, orderId);
    }

    @RequestMapping("/record/save")
    public boolean saveBoxLocation(@RequestBody BoxLocationRecordInfo boxLocationRecordInfo, String entCode) {
        return boxLocationRecordService.saveBoxLocation(boxLocationRecordInfo, entCode);
    }


}

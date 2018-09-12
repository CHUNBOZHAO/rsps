package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.domain.automatic.RecycleApplyInfo;
import com.izhuixin.rsps.service.RecycleApplyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/recycleData/applyInfo")
public class RecycleApplyInfoController {

    @Autowired
    private RecycleApplyInfoService recycleApplyInfoService;

    @RequestMapping("/save")
    public boolean saveInfo(@RequestBody RecycleApplyInfo recycleApplyInfo) {
        return recycleApplyInfoService.saveInfo(recycleApplyInfo);
    }

    @RequestMapping("/count")
    public Integer countInfo(String userId, String boxId) {
        return recycleApplyInfoService.countInfo(userId, boxId);
    }

    @RequestMapping("/updateState")
    public boolean updateState(String boxId, Byte recycleType) {
        return recycleApplyInfoService.updateState(boxId, recycleType);
    }
}

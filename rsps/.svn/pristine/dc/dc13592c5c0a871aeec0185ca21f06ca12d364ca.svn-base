package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.wms.Assignment;
import com.izhuixin.rsps.common.vo.wms.BoxBindingInfo;
import com.izhuixin.rsps.service.BoxBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("wms/v1/")
@RestController
public class RestWmsController {

    @Autowired
    private BoxBindService boxBindService;

    @RequestMapping("/bindBox/{entCode}")
    public String bingBox(@RequestBody BoxBindingInfo boxBindingInfo, @PathVariable("entCode") String entCode) {
        boxBindingInfo.setEntCode(entCode.concat("_"));
        return boxBindService.bindBox(boxBindingInfo);
    }

    @RequestMapping("/bindBox")
    public String bingBox(@RequestBody BoxBindingInfo boxBindingInfo) {
        boxBindingInfo.setEntCode("jhyt".concat("_"));
        return boxBindService.bindBox(boxBindingInfo);
    }

    @RequestMapping("/assignBox/{entCode}")
    public String assignBox(@RequestBody Assignment assignment, @PathVariable("entCode") String entCode) {
        assignment.setEntCode(entCode.concat("_"));
        return boxBindService.assignBox(assignment);
    }

    @RequestMapping("/assignBox")
    public String assignBox(@RequestBody Assignment assignment) {
        assignment.setEntCode("jhyt".concat("_"));
        return boxBindService.assignBox(assignment);
    }

}

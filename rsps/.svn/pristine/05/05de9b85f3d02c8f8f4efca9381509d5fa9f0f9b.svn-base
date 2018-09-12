package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.api.SystemParamReq;
import com.izhuixin.rsps.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sysData")
public class SystemParamController {

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping("/system/param/update")
    public String updateSystemParam(@RequestBody SystemParamReq systemParamReq) {
        return sysParamService.updateSysParam(systemParamReq);
    }

}

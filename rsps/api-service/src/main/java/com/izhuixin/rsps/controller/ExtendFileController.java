package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.service.ExtendFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pathData/")
public class ExtendFileController {

    @Autowired
    private ExtendFileService extendFileService;

    /**
     * 获取报表文件本地路径
     * @param entCode
     * @param fileName
     * @return
     */
    @RequestMapping("/report/localPath/get/{entCode}/{fileName}")
    public String getReportLocalPath(String entCode, String fileName) {
        return extendFileService.getReportLocalPath(entCode, fileName);
    }

    /**
     * 获取报表文件网络路径
     * @param entCode
     * @param fileName
     * @return
     */
    @RequestMapping("/report/networkPath/get/{entCode}/{fileName}")
    public String getReportNetworkPath(String entCode, String fileName) {
        return extendFileService.getReportNetworkPath(entCode, fileName);
    }
}

package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.service.ExtendFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Service
public class ExtendFileServiceImpl implements ExtendFileService {

    @Value("${local.path}")
    private String localPath;

    @Value("${network.path}")
    private String networkPath;

    /**
     *
     * @param fileName
     * @return
     */
    @Override
    public String getReportLocalPath(String entCode, String fileName) {
        String path = localPath + "/files/rsps/report/" + entCode;
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return path + fileName;
    }

    /**
     *
     * @param fileName
     * @return
     */
    @Override
    public String getReportNetworkPath(String entCode, String fileName) {
        if (isNotBlank(fileName)) {
            return networkPath + "/files/rsps/report/" + entCode + "/" + fileName;
        } else {
            return networkPath + "/files/rsps/report/" + entCode;
        }
    }

}

package com.izhuixin.rsps.model;

import java.io.Serializable;

/**
 * WMS请求消息基本信息
 */
public class WmsReqBase implements Serializable {

    /** 企业编码 */
    private String entCode;

    /** access token */
    private String accessToken;

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

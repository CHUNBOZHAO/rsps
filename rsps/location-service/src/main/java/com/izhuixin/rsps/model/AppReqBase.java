package com.izhuixin.rsps.model;

import java.io.Serializable;

/**
 * App请求基础参数
 */
public class AppReqBase implements Serializable {

    /***/
    private String tableId;

    /**
     * 访问编码
     */
    private String ak;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 会话ID 判断用户重复登录
     */
    private String sessionId;

    /**
     * PAD ID 以此来识别企业
     */
    private String padId;

    /**
     * 企业编码
     */
    private String entCode;

    /** 操作人ID wms提供  必选 */
    private String operatorId;

    /**
     * 国际移动设备识别码
     */
    private String imei;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPadId() {
        return padId;
    }

    public void setPadId(String padId) {
        this.padId = padId;
    }

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}
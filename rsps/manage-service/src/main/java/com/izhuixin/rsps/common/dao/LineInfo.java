package com.izhuixin.rsps.common.dao;

/**
 * 线路信息
 */
public class LineInfo {

    /** 线路ID */
    private String lineId;

    /** 线路名称 */
    private String lineName;

    /** 用户ID */
    private String userId;

    /** 关联标示 */
    private byte flag;

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

package com.izhuixin.rsps.common.vo.app;

/**
 * 线路实体信息
 */
public class AppLinesEntity {

    /** 线路ID */
    private String lineId;

    /** 线路名称 */
    private String lineName;

    /** 关联关系  0:无关联，1:有关联 */
    private String flag;

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

package com.izhuixin.rsps.domain.manual;

/**
 * 线路关联客户数量集合
 */
public class RelatedCustomCountInfo {

    private String lineId;

    private Integer customCount;

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public Integer getCustomCount() {
        return customCount;
    }

    public void setCustomCount(Integer customCount) {
        this.customCount = customCount;
    }
}

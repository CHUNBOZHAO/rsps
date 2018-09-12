package com.izhuixin.rsps.common.vo.app;

import java.util.List;

/**
 * 请求设置线路信息
 */
public class AppReqLineSetting extends AppReqBase{

    /** 操作人ID */
    private String operatorId;

    private List<String> lineIds;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public List<String> getLineIds() {
        return lineIds;
    }

    public void setLineIds(List<String> lineIds) {
        this.lineIds = lineIds;
    }
}

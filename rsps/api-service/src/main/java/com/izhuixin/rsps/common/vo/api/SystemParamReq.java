package com.izhuixin.rsps.common.vo.api;

import java.io.Serializable;

/**
 * 系统参数请求消息定义
 */
public class SystemParamReq implements Serializable{

    /** 参数名称 */
    private String paramName;

    /** 参数值 */
    private String paramValue;

    /** 参数描述 */
    private String paramDescr;

    /** 有效状态 */
    private Byte paramStatus;

    /** 操作人 */
    private Integer operatorId;

    /** 备注 */
    private String paramRemark;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDescr() {
        return paramDescr;
    }

    public void setParamDescr(String paramDescr) {
        this.paramDescr = paramDescr;
    }

    public Byte getParamStatus() {
        return paramStatus;
    }

    public void setParamStatus(Byte paramStatus) {
        this.paramStatus = paramStatus;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getParamRemark() {
        return paramRemark;
    }

    public void setParamRemark(String paramRemark) {
        this.paramRemark = paramRemark;
    }
}

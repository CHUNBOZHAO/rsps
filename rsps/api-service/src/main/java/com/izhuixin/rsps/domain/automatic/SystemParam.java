package com.izhuixin.rsps.domain.automatic;

import java.io.Serializable;
import java.util.Date;

public class SystemParam implements Serializable {
    private Long id;

    private String paramName;

    private String paramValue;

    private String paramDescr;

    private Byte paramStatus;

    private Date updateTime;

    private Integer operatorId;

    private String paramRemark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public String getParamDescr() {
        return paramDescr;
    }

    public void setParamDescr(String paramDescr) {
        this.paramDescr = paramDescr == null ? null : paramDescr.trim();
    }

    public Byte getParamStatus() {
        return paramStatus;
    }

    public void setParamStatus(Byte paramStatus) {
        this.paramStatus = paramStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        this.paramRemark = paramRemark == null ? null : paramRemark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paramName=").append(paramName);
        sb.append(", paramValue=").append(paramValue);
        sb.append(", paramDescr=").append(paramDescr);
        sb.append(", paramStatus=").append(paramStatus);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", paramRemark=").append(paramRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
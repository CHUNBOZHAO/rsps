package com.izhuixin.rsps.domain.automatic;

import java.io.Serializable;
import java.util.Date;

public class BoxInfoDO implements Serializable {
    private Long id;

    private String rfid;

    private String barcode;

    private Byte status;

    private String operatorId;

    private String operator;

    private Date operateTime;

    private String duAddress;

    private Double duLatitude;

    private Double duLongitude;

    private Byte duCoordType;

    private String duTableId;

    private Date updateTime;

    private String orderId;

    private String boxName;

    private String nextOperatorId;

    private Date bindTime;

    private String detail;

    private Byte signStatus;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getDuAddress() {
        return duAddress;
    }

    public void setDuAddress(String duAddress) {
        this.duAddress = duAddress == null ? null : duAddress.trim();
    }

    public Double getDuLatitude() {
        return duLatitude;
    }

    public void setDuLatitude(Double duLatitude) {
        this.duLatitude = duLatitude;
    }

    public Double getDuLongitude() {
        return duLongitude;
    }

    public void setDuLongitude(Double duLongitude) {
        this.duLongitude = duLongitude;
    }

    public Byte getDuCoordType() {
        return duCoordType;
    }

    public void setDuCoordType(Byte duCoordType) {
        this.duCoordType = duCoordType;
    }

    public String getDuTableId() {
        return duTableId;
    }

    public void setDuTableId(String duTableId) {
        this.duTableId = duTableId == null ? null : duTableId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
    }

    public String getNextOperatorId() {
        return nextOperatorId;
    }

    public void setNextOperatorId(String nextOperatorId) {
        this.nextOperatorId = nextOperatorId == null ? null : nextOperatorId.trim();
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Byte getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Byte signStatus) {
        this.signStatus = signStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rfid=").append(rfid);
        sb.append(", barcode=").append(barcode);
        sb.append(", status=").append(status);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operator=").append(operator);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", duAddress=").append(duAddress);
        sb.append(", duLatitude=").append(duLatitude);
        sb.append(", duLongitude=").append(duLongitude);
        sb.append(", duCoordType=").append(duCoordType);
        sb.append(", duTableId=").append(duTableId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", orderId=").append(orderId);
        sb.append(", boxName=").append(boxName);
        sb.append(", nextOperatorId=").append(nextOperatorId);
        sb.append(", bindTime=").append(bindTime);
        sb.append(", detail=").append(detail);
        sb.append(", signStatus=").append(signStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
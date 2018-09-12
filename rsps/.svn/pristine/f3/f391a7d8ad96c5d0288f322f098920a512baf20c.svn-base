package com.izhuixin.rsps.domain.automatic;

import java.io.Serializable;
import java.util.Date;

public class BoxLocationRecordDO implements Serializable {
    private Long id;

    private String rfid;

    private String orderId;

    private String duAddress;

    private Double duLatitude;

    private Double duLongitude;

    private Byte duCoordType;

    private Date createTime;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rfid=").append(rfid);
        sb.append(", orderId=").append(orderId);
        sb.append(", duAddress=").append(duAddress);
        sb.append(", duLatitude=").append(duLatitude);
        sb.append(", duLongitude=").append(duLongitude);
        sb.append(", duCoordType=").append(duCoordType);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
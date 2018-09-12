package com.izhuixin.rsps.common.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单配送信息
 */
public class OrderDriverInfo implements Serializable {

    private Long id;

    private String orderId;

    private String driverId;

    private String driverName;

    private String driverPhone;

    private Byte driverOrder;

    private Date createTime;

    private String createTimeStr;

    private String truckNumber;

    private String nextDriverId;

    private Byte transportType;

    private Byte state;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Byte getDriverOrder() {
        return driverOrder;
    }

    public void setDriverOrder(Byte driverOrder) {
        this.driverOrder = driverOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getNextDriverId() {
        return nextDriverId;
    }

    public void setNextDriverId(String nextDriverId) {
        this.nextDriverId = nextDriverId;
    }

    public Byte getTransportType() {
        return transportType;
    }

    public void setTransportType(Byte transportType) {
        this.transportType = transportType;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}

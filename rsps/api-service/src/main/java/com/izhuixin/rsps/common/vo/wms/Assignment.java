package com.izhuixin.rsps.common.vo.wms;

import java.util.List;

/**
 * 配送的任务
 */
public class Assignment extends WmsReqBase{

    /** 订单ID */
    private String orderId;

    /** 司机信息 */
    private List<DriverInfo> drivers;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<DriverInfo> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverInfo> drivers) {
        this.drivers = drivers;
    }
}

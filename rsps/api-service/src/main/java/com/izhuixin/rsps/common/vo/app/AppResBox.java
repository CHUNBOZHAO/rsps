package com.izhuixin.rsps.common.vo.app;

import java.io.Serializable;

/**
 * 派货订单包装箱信息
 */
public class AppResBox implements Serializable {

    /** 包装箱ID rfid */
    private String boxId;

    /** 蓝牙Mac */
    private String bleMac;

    /** 条形码 */
    private String barcode;

    /** 包装箱名称  */
    private String boxName;

    /** 包装箱状态 LEISURE(0, "闲置"), BINDING(1, "配货"), TRANSPORTING(2, "运输"), RETENTION(3, "滞留"), RECYCLE(4, "回收") */
    private Byte boxStatus;

    /** 药品清单 */
    private String detail;

    /** 客户ID */
    private String customId;

    /** 客户名称 */
    private String customName;

    /** 当前操作人ID */
    private String operatorId;

    /** 当前操作人名称 */
    private String operatorName;

    /** 操作时间 格式：yyyy-MM-dd HH:mm:ss */
    private String operatorDate;

    /** 下一个中转司机ID 如果为空，则无需中转 */
    private String nextDriverId;

    /** 下一个中转司机名称 */
    private String nextDriverName;

    /** 订单ID wms提供 */
    private String orderId;

    /** 包裹签收状态 */
    private Byte signStatus;

    /** 包装箱当前位置 */
    private String duAddress;

    /** 包装箱当前纬度 */
    private Double duLatitude;

    /** 包装箱当前经度 */
    private Double duLongitude;

    /** 包装箱当前坐标类型 */
    private Byte duCoordType;

    /** 下一个ID */
    private String nextStationId;

    /** 下一个站点 */
    private String nextStation;

    /** 订单信息 */
    private AppOrderEntity appOrderEntity;

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getBleMac() {
        return bleMac;
    }

    public void setBleMac(String bleMac) {
        this.bleMac = bleMac;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public Byte getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Byte boxStatus) {
        this.boxStatus = boxStatus;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getNextDriverId() {
        return nextDriverId;
    }

    public void setNextDriverId(String nextDriverId) {
        this.nextDriverId = nextDriverId;
    }

    public String getNextDriverName() {
        return nextDriverName;
    }

    public void setNextDriverName(String nextDriverName) {
        this.nextDriverName = nextDriverName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(String operatorDate) {
        this.operatorDate = operatorDate;
    }

    public Byte getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Byte signStatus) {
        this.signStatus = signStatus;
    }

    public String getDuAddress() {
        return duAddress;
    }

    public void setDuAddress(String duAddress) {
        this.duAddress = duAddress;
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

    public AppOrderEntity getAppOrderEntity() {
        return appOrderEntity;
    }

    public void setAppOrderEntity(AppOrderEntity appOrderEntity) {
        this.appOrderEntity = appOrderEntity;
    }

    public String getNextStationId() {
        return nextStationId;
    }

    public void setNextStationId(String nextStationId) {
        this.nextStationId = nextStationId;
    }

    public String getNextStation() {
        return nextStation;
    }

    public void setNextStation(String nextStation) {
        this.nextStation = nextStation;
    }
}

package com.izhuixin.rsps.common.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 箱子基本信息
 */
public class BoxInfo implements Serializable{

    private Long id;

    private String rfid;

    private String barcode;

    private Byte status;

    private String operatorId;

    private String operator;

    private Date operateTime;

    private String operateTimeStr;

    private String duAddress;

    private Double duLatitude;

    private Double duLongitude;

    private Byte duCoordType;

    private String duTableId;

    private Date updateTime;

    private String updateTimeStr;

    private String orderId;

    private String boxName;

    private String nextOperatorId;

    private String detail;

    private Date bindTime;

    private String bindTimeStr;

    /** 包装箱类型 */
    private Byte boxType;

    /** 蓝牙mac地址 */
    private String btMac;

    /** 创建时间 */
    private Date createTime;

    /** 创建时间 */
    private String createTimeStr;

    /** 包装箱系统生成唯一ID */
    private String uniqueId;

    /** 签收状态 */
    private Byte signStatus;

    /** 循环次数 */
    private Integer cycleIndex;

    /** 客户ID */
    private String customId;

    /** 客户名称 */
    private String customName;

    /** 起始站点 */
    private String beginTransferId;

    /** 中转站ID */
    private String nextTransferId;

    /** 中转站名称 */
    private String nextTransferName;

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
        this.rfid = rfid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateTimeStr() {
        return operateTimeStr;
    }

    public void setOperateTimeStr(String operateTimeStr) {
        this.operateTimeStr = operateTimeStr;
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

    public String getDuTableId() {
        return duTableId;
    }

    public void setDuTableId(String duTableId) {
        this.duTableId = duTableId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getNextOperatorId() {
        return nextOperatorId;
    }

    public void setNextOperatorId(String nextOperatorId) {
        this.nextOperatorId = nextOperatorId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getBindTimeStr() {
        return bindTimeStr;
    }

    public void setBindTimeStr(String bindTimeStr) {
        this.bindTimeStr = bindTimeStr;
    }

    public Byte getBoxType() {
        return boxType;
    }

    public void setBoxType(Byte boxType) {
        this.boxType = boxType;
    }

    public String getBtMac() {
        return btMac;
    }

    public void setBtMac(String btMac) {
        this.btMac = btMac;
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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Byte getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Byte signStatus) {
        this.signStatus = signStatus;
    }

    public Integer getCycleIndex() {
        return cycleIndex;
    }

    public void setCycleIndex(Integer cycleIndex) {
        this.cycleIndex = cycleIndex;
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

    public String getBeginTransferId() {
        return beginTransferId;
    }

    public void setBeginTransferId(String beginTransferId) {
        this.beginTransferId = beginTransferId;
    }

    public String getNextTransferId() {
        return nextTransferId;
    }

    public void setNextTransferId(String nextTransferId) {
        this.nextTransferId = nextTransferId;
    }

    public String getNextTransferName() {
        return nextTransferName;
    }

    public void setNextTransferName(String nextTransferName) {
        this.nextTransferName = nextTransferName;
    }
}

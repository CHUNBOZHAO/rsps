package com.izhuixin.rsps.common.vo.web;

import java.io.Serializable;
import java.util.Date;

/***
 * 包装箱页面展示
 */
public class BoxInfoVO implements Serializable {

    /** 包装箱ID */
    private String rfid;

    /** 清单条码 */
    private String barcode;

    /** 包裹名称 */
    private String boxName;

    /** 状态 */
    private Byte status;

    /** 状态描述 */
    private String statusDescr;

    /** 操作人ID */
    private String operatorId;

    /** 操作人名称 */
    private String operator;

    /** 操作时间 */
    private Date operateTime;

    /** 订单ID */
    private String orderId;

    /** 包装箱类型 */
    private Byte boxType;

    /** 蓝牙mac地址 */
    private String btMac;

    /**  创建时间 */
    private Date createTime;

    /**  签收状态 */
    private Byte signStatus;

    /** 包装箱系统生成唯一ID */
    private String uniqueId;

    /** 持续时间 */
    private String duration;

    /** 循环次数 */
    private Integer cycleIndex;

    /** 寄货人 */
    private String sender;

    /** 寄货人区域 */
    private String senderArea;

    /** 收货人 */
    private String receiver;

    /** 收货人区域 */
    private String receiverArea;

    /** 方向  1： 寄  2：收 */
    private Byte direction;

    /** 包装箱类型 */
    private BoxTypeVO boxTypeVO;

    /** 包装箱回收状态 */
    private Byte recycleType;

    /** 订单来源 B端、C端  */
    private Byte orderSource;

    /** 客户ID */
    private String customId;

    /** 客户名称 */
    private String customName;

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

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getStatusDescr() {
        return statusDescr;
    }

    public void setStatusDescr(String statusDescr) {
        this.statusDescr = statusDescr;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Byte getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Byte signStatus) {
        this.signStatus = signStatus;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getCycleIndex() {
        return cycleIndex;
    }

    public void setCycleIndex(Integer cycleIndex) {
        this.cycleIndex = cycleIndex;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderArea() {
        return senderArea;
    }

    public void setSenderArea(String senderArea) {
        this.senderArea = senderArea;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public Byte getDirection() {
        return direction;
    }

    public void setDirection(Byte direction) {
        this.direction = direction;
    }

    public BoxTypeVO getBoxTypeVO() {
        return boxTypeVO;
    }

    public void setBoxTypeVO(BoxTypeVO boxTypeVO) {
        this.boxTypeVO = boxTypeVO;
    }

    public Byte getRecycleType() {
        return recycleType;
    }

    public void setRecycleType(Byte recycleType) {
        this.recycleType = recycleType;
    }

    public Byte getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Byte orderSource) {
        this.orderSource = orderSource;
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
}

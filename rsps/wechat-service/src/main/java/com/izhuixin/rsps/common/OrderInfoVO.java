package com.izhuixin.rsps.common;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 */
public class OrderInfoVO implements Serializable{

    /** id */
    private Long id;

    /** 订单ID */
    private String orderId;

    /** 用户ID */
    private String customerId;

    /** 用户名 */
    private String customer;

    /** 寄件人ID */
    private String senderId;

    /** 寄件人名称 */
    private String senderName;

    /** 寄件人电话 */
    private String senderTel;

    /** 寄件人 省/自治区 */
    private String senderArea1;

    /** 寄件人 市 */
    private String senderArea2;

    /** 寄件人 区/县 */
    private String senderArea3;

    /** 寄件人区域代码 */
    private String senderAreaCode;

    /** 寄件人地址 */
    private String senderAddress;

    /** 收货人ID */
    private String receiverId;

    /** 收货人姓名 */
    private String receiverName;

    /** 收货人电话 */
    private String receiverTel;

    /** 省/自治区 */
    private String receiverArea1;

    /** 市 */
    private String receiverArea2;

    /** 区/县 */
    private String receiverArea3;

    /** 收货人区域代码 */
    private String receiverAreaCode;

    /** 收货人地址 */
    private String receiverAddress;

    /** 订单类型 */
    private Byte orderType;

    private String detail;

    private Byte state;

    private Byte status;

    private Date createTime;

    private Date modifyTime;

    private String evaluation;

    private List<BoxTypeVO> boxes = Lists.newArrayList();

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderTel() {
        return senderTel;
    }

    public void setSenderTel(String senderTel) {
        this.senderTel = senderTel;
    }

    public String getSenderAreaCode() {
        return senderAreaCode;
    }

    public void setSenderAreaCode(String senderAreaCode) {
        this.senderAreaCode = senderAreaCode;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverAreaCode() {
        return receiverAreaCode;
    }

    public void setReceiverAreaCode(String receiverAreaCode) {
        this.receiverAreaCode = receiverAreaCode;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public String getSenderArea1() {
        return senderArea1;
    }

    public void setSenderArea1(String senderArea1) {
        this.senderArea1 = senderArea1;
    }

    public String getSenderArea2() {
        return senderArea2;
    }

    public void setSenderArea2(String senderArea2) {
        this.senderArea2 = senderArea2;
    }

    public String getSenderArea3() {
        return senderArea3;
    }

    public void setSenderArea3(String senderArea3) {
        this.senderArea3 = senderArea3;
    }

    public String getReceiverArea1() {
        return receiverArea1;
    }

    public void setReceiverArea1(String receiverArea1) {
        this.receiverArea1 = receiverArea1;
    }

    public String getReceiverArea2() {
        return receiverArea2;
    }

    public void setReceiverArea2(String receiverArea2) {
        this.receiverArea2 = receiverArea2;
    }

    public String getReceiverArea3() {
        return receiverArea3;
    }

    public void setReceiverArea3(String receiverArea3) {
        this.receiverArea3 = receiverArea3;
    }

    public List<BoxTypeVO> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<BoxTypeVO> boxes) {
        this.boxes = boxes;
    }
}

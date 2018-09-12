package com.izhuixin.rsps.domain.automatic;

import java.io.Serializable;
import java.util.Date;

public class OrderDO implements Serializable {
    private Long id;

    private String orderId;

    private String customerId;

    private String customer;

    private String senderId;

    private String senderName;

    private String senderTel;

    private String senderAreaCode;

    private String senderAddress;

    private String detail;

    private Byte state;

    private Byte status;

    private Date createTime;

    private Date modifyTime;

    private String evaluation;

    private String receiverId;

    private String receiverName;

    private String receiverTel;

    private String receiverAreaCode;

    private String receiverAddress;

    private Byte orderType;

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
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getSenderTel() {
        return senderTel;
    }

    public void setSenderTel(String senderTel) {
        this.senderTel = senderTel == null ? null : senderTel.trim();
    }

    public String getSenderAreaCode() {
        return senderAreaCode;
    }

    public void setSenderAreaCode(String senderAreaCode) {
        this.senderAreaCode = senderAreaCode == null ? null : senderAreaCode.trim();
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress == null ? null : senderAddress.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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
        this.evaluation = evaluation == null ? null : evaluation.trim();
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel == null ? null : receiverTel.trim();
    }

    public String getReceiverAreaCode() {
        return receiverAreaCode;
    }

    public void setReceiverAreaCode(String receiverAreaCode) {
        this.receiverAreaCode = receiverAreaCode == null ? null : receiverAreaCode.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", customerId=").append(customerId);
        sb.append(", customer=").append(customer);
        sb.append(", senderId=").append(senderId);
        sb.append(", senderName=").append(senderName);
        sb.append(", senderTel=").append(senderTel);
        sb.append(", senderAreaCode=").append(senderAreaCode);
        sb.append(", senderAddress=").append(senderAddress);
        sb.append(", detail=").append(detail);
        sb.append(", state=").append(state);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", evaluation=").append(evaluation);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverTel=").append(receiverTel);
        sb.append(", receiverAreaCode=").append(receiverAreaCode);
        sb.append(", receiverAddress=").append(receiverAddress);
        sb.append(", orderType=").append(orderType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
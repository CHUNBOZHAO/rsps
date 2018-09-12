package com.izhuixin.rsps.common.vo.web;

import java.io.Serializable;
import java.util.Date;

/**
 * 线路基本信息
 */
public class LineInfoVO implements Serializable {

    /** 自增ID */
    private Long id;

    /** 线路ID */
    private String lineId;

    /** 线路名称 */
    private String lineName;

    /** 创建时间 */
    private Date createTime;

    /** 创建时间 */
    private String createTimeStr;

    /** 修改时间 */
    private Date modifyTime;

    /** 修改时间 */
    private String modifyTimeStr;

    /** 描述 */
    private String descr;

    /** 线路路径 */
    private String track;

    /** 用户ID */
    private String userId;

    /** 用户昵称 */
    private String userNickName;

    /** 关联配送员数量 */
    private Integer relatedOperatorCount;

    /** 关联客户数量 */
    private Integer relatedCustomCount;

    /** 关联中转站数量 */
    private Integer relatedTransferCount;

    /** 少量关联配送员信息 */
    private String bitRelatedOperator;

    /** 所有关联配送员信息 */
    private String allRelatedOperator;

    /** 少量关联中转站信息 */
    private String bitRelatedTransfer;

    /** 所有关联中转站信息 */
    private String allRelatedTransfer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyTimeStr() {
        return modifyTimeStr;
    }

    public void setModifyTimeStr(String modifyTimeStr) {
        this.modifyTimeStr = modifyTimeStr;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getRelatedCustomCount() {
        return relatedCustomCount;
    }

    public void setRelatedCustomCount(Integer relatedCustomCount) {
        this.relatedCustomCount = relatedCustomCount;
    }

    public Integer getRelatedTransferCount() {
        return relatedTransferCount;
    }

    public void setRelatedTransferCount(Integer relatedTransferCount) {
        this.relatedTransferCount = relatedTransferCount;
    }

    public Integer getRelatedOperatorCount() {
        return relatedOperatorCount;
    }

    public void setRelatedOperatorCount(Integer relatedOperatorCount) {
        this.relatedOperatorCount = relatedOperatorCount;
    }

    public String getBitRelatedOperator() {
        return bitRelatedOperator;
    }

    public void setBitRelatedOperator(String bitRelatedOperator) {
        this.bitRelatedOperator = bitRelatedOperator;
    }

    public String getAllRelatedOperator() {
        return allRelatedOperator;
    }

    public void setAllRelatedOperator(String allRelatedOperator) {
        this.allRelatedOperator = allRelatedOperator;
    }

    public String getBitRelatedTransfer() {
        return bitRelatedTransfer;
    }

    public void setBitRelatedTransfer(String bitRelatedTransfer) {
        this.bitRelatedTransfer = bitRelatedTransfer;
    }

    public String getAllRelatedTransfer() {
        return allRelatedTransfer;
    }

    public void setAllRelatedTransfer(String allRelatedTransfer) {
        this.allRelatedTransfer = allRelatedTransfer;
    }
}

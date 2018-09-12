package com.izhuixin.rsps.common.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 线路基本信息
 */
public class LineEntityInfo implements Serializable {

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

    /** 所属用户ID */
    private String userId;

    /** 用户昵称 */
    private String userNickName;

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
}

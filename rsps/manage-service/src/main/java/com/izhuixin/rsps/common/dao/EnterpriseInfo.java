package com.izhuixin.rsps.common.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业信息
 */
public class EnterpriseInfo implements Serializable{

    private Long id;

    private String userName;

    private String userPwd;

    private String entId;

    private String entName;

    private String entAddress;

    private String entPostcode;

    private String entCode;

    private Date createTime;

    private String keyId;

    private String keySecret;

    private String ak;

    private Byte status;

    private Byte type;

    private Byte accessWay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getEntPostcode() {
        return entPostcode;
    }

    public void setEntPostcode(String entPostcode) {
        this.entPostcode = entPostcode;
    }

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getAccessWay() {
        return accessWay;
    }

    public void setAccessWay(Byte accessWay) {
        this.accessWay = accessWay;
    }

    public String getEntAddress() {
        return entAddress;
    }

    public void setEntAddress(String entAddress) {
        this.entAddress = entAddress;
    }
}


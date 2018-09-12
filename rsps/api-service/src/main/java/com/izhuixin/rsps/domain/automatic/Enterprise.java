package com.izhuixin.rsps.domain.automatic;

import java.io.Serializable;
import java.util.Date;

public class Enterprise implements Serializable {
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

    private static final long serialVersionUID = 1L;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId == null ? null : entId.trim();
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName == null ? null : entName.trim();
    }

    public String getEntAddress() {
        return entAddress;
    }

    public void setEntAddress(String entAddress) {
        this.entAddress = entAddress == null ? null : entAddress.trim();
    }

    public String getEntPostcode() {
        return entPostcode;
    }

    public void setEntPostcode(String entPostcode) {
        this.entPostcode = entPostcode == null ? null : entPostcode.trim();
    }

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode == null ? null : entCode.trim();
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
        this.keyId = keyId == null ? null : keyId.trim();
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret == null ? null : keySecret.trim();
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak == null ? null : ak.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", entId=").append(entId);
        sb.append(", entName=").append(entName);
        sb.append(", entAddress=").append(entAddress);
        sb.append(", entPostcode=").append(entPostcode);
        sb.append(", entCode=").append(entCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", keyId=").append(keyId);
        sb.append(", keySecret=").append(keySecret);
        sb.append(", ak=").append(ak);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", accessWay=").append(accessWay);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
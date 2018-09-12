package com.izhuixin.rsps.common;

import java.io.Serializable;
import java.util.List;

/**
 * RSPS user
 */
public class ManagerUserDetail implements Serializable {

    /** ID */
    private Long id;

    /** User ID wms平台提供 */
    private String userId;

    /** 用户名称 */
    private String userName;

    /** 用户类型 */
    private byte userType;

    /** 企业名称 */
    private String companyName;

    /** 企业编码 */
    private String entCode;

    /** 企业ID */
    private String entId;

    /** 昵称 */
    private String nickName;

    /** 等级 */
    private byte level;

    /** 权限列表 */
    private List<String> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public byte getUserType() {
        return userType;
    }

    public void setUserType(byte userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}


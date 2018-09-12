package com.izhuixin.rsps.model;

/**
 * app用户登录消息实体
 */
public class AppReqLoginUser extends AppReqBase {

    private String userName;

    private String userPwd;

    private String newUserPwd;

    private Byte userType;

    /** 强制登录状态 1：强制登录 */
    private Byte kickFlag;

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

    public String getNewUserPwd() {
        return newUserPwd;
    }

    public void setNewUserPwd(String newUserPwd) {
        this.newUserPwd = newUserPwd;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Byte getKickFlag() {
        return kickFlag;
    }

    public void setKickFlag(Byte kickFlag) {
        this.kickFlag = kickFlag;
    }
}

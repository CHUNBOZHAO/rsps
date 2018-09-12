package com.izhuixin.rsps.common.vo.app;

/**
 * 操作人员注册
 */
public class AppReqOpRegister extends AppReqBase {

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

package com.izhuixin.rsps.common.vo.app;

/**
 * 操作人信息
 */
public class AppResOperator extends AppAckContent {

    /** 自增ID */
    private Long id;

    /** wms提供ID */
    private String operatorId;

    /** 用户名称 */
    private String userName;

    /** 用户类型 */
    private Byte userType;

    /** 头像连接地址 */
    private String headUrl;

    /** 年龄 */
    private Byte age;

    /** 性别 */
    private Byte sex;

    /** 登录SessionId */
    private String appSessionId;

    /** 企业编码 */
    private String entCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getAppSessionId() {
        return appSessionId;
    }

    public void setAppSessionId(String appSessionId) {
        this.appSessionId = appSessionId;
    }

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }
}

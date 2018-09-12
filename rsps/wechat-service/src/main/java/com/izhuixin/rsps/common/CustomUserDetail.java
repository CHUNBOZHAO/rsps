package com.izhuixin.rsps.common;

/**
 * custom user
 */
public class CustomUserDetail {

    /** ID */
    private Long id;

    /** User ID wms平台提供 */
    private String userId;

    /** 用户类型 */
    private byte userType;

    /** 用户名称 */
    private String name;

    /** 用户手机 */
    private String tel;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return name;
    }

    public CustomUserDetail() {
        id = 1L;
        userId = "100722";
        userType = 1;
        name = "东阳市喜洋洋医院";
        tel = "111";
    }
}


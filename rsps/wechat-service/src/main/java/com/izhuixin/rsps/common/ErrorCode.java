package com.izhuixin.rsps.common;

/**
 * 错误码定义
 */
public enum ErrorCode {

    OK(0, "ok"),
    USER_LOGIN_SESSION_INVALID(100001, "用户登录session失效"),
    USER_NAME_PWD_ERROR(200001, "用户名或密码错误"),
    USER_NAME_NOT_EXIST(200002, "用户不存在"),
    USER_PWD_ERROR(200003, "用户密码不正确"),
    USER_NAME_EXIST(200004, "用户名已存在"),
    DB_ERROR(900001, "数据库错误"),
    BOX_QUERY_EMPTY(300001, "查询包装箱不存在"),
    DATA_PUSH_IS_NULL(400001, "推送数据为空"),
    REQUEST_PARAM_ERROR(500001, "请求参数错误"),
    ORDER_CREATE_ERROR(600001, "下单失败"),
    ORDER_CREATE_NO_RECEIVING_ERROR(600002,"下单失败，附近无人接单"),
    VERSION_APP_QUERY_ERROR(10, "查询App版本出现错误"),
    ERROR(1, "ERROR");

    private Integer index;

    private String descr;

    ErrorCode(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }

    public static String getDesc(int index) {
        for (ErrorCode c : ErrorCode.values()) {
            if (c.getIndex() == index) {
                return c.descr;
            }
        }
        return null;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}

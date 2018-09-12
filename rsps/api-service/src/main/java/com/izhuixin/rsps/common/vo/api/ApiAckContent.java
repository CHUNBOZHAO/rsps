package com.izhuixin.rsps.common.vo.api;

public class ApiAckContent {

    /** 返回码  0：成功  其他待定 */
    private String status;

    /** 返回信息 */
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
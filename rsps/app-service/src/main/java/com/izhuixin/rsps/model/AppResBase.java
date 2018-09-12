package com.izhuixin.rsps.model;

/***
 * 返回数据基本信息
 */
public class AppResBase {

    /** 返回码 */
    private Integer status;

    /** 返回码信息 */
    private String message;

    /** 返回数据个数 */
    private Integer size;

    /** 符合条件数据总数 */
    private Integer total;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

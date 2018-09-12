package com.izhuixin.rsps.model;

import java.util.List;

/**
 * 客户信息
 */
public class CustomerInfo {

    /** 客户ID */
    private String id;

    /** 客户名称 */
    private String name;

    /** 客户地址 */
    private String address;

    /** 联系电话 */
    private String phone;

    /** 客户联系人 */
    private List<LinkerInfo> contacts;

    /** 客户信息最后更新时间 */
    private String lastModify;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<LinkerInfo> getContacts() {
        return contacts;
    }

    public void setContacts(List<LinkerInfo> contacts) {
        this.contacts = contacts;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

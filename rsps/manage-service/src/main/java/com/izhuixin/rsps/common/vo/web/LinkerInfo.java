package com.izhuixin.rsps.common.vo.web;

/**
 * 客户联系人信息
 */
public class LinkerInfo {

    /** 联系人ID */
    private Long id;

    /** WMS 联系人ID */
    private String linkerId;

    /** 联系人名称 */
    private String name;

    /** 联系人电话 */
    private String phone;

    public String getLinkerId() {
        return linkerId;
    }

    public void setLinkerId(String linkerId) {
        this.linkerId = linkerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

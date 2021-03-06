package com.izhuixin.rsps.common.vo.web;

import java.util.Date;
import java.util.List;

/**
 * 客户信息
 */
public class CustomerInfoVO {

    /** 客户ID */
    private Long id;

    /** 客户ID */
    private String customerId;

    /** 客户名称 */
    private String name;

    /** 客户地址 */
    private String address;

    /** 客户联系人 */
    private List<LinkerInfo> contacts;

    /** 客户信息最后更新时间 */
    private Date lastModify;

    /** 线路ID */
    private String lineId;

    /** 线路关联 */
    private boolean lineRelated;

    /** 关联线路 */
    private String relatedLines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public boolean isLineRelated() {
        return lineRelated;
    }

    public void setLineRelated(boolean lineRelated) {
        this.lineRelated = lineRelated;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getRelatedLines() {
        return relatedLines;
    }

    public void setRelatedLines(String relatedLines) {
        this.relatedLines = relatedLines;
    }
}

package com.izhuixin.rsps.common.vo.wms;

/**
 * 客户联系人信息
 */
public class LinkerInfo {

    /** 联系人ID */
    private String id;

    /** 联系人名称 */
    private String name;

    /** 联系人电话 */
    private String phone;

    /** 联系人信息最后更改时间 */
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }
}

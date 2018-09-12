package com.izhuixin.rsps.common.constant;

/**
 * 用户类型
 */
public enum ManagerUserType {

    common(0, "一般用户"), site(1, "站点");

    private Integer index;

    private String descr;

    ManagerUserType(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
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

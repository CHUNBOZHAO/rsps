package com.izhuixin.rsps.common.constant;

/**
 * 用户类型
 */
public enum UserType {

    MANAGER(0, "管理"), CUSTOM(1, "客户");

    private Integer index;

    private String descr;

    UserType(Integer index, String descr) {
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

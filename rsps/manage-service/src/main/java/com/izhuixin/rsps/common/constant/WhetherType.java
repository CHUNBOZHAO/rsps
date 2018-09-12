package com.izhuixin.rsps.common.constant;

public enum WhetherType {

    NO(0, "否"), YES(1, "是");

    private Integer index;

    private String descr;

    WhetherType(Integer index, String descr) {
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

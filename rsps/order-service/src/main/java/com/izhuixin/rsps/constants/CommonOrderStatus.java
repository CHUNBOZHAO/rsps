package com.izhuixin.rsps.constants;

public enum CommonOrderStatus {

    UNSIGN(0,"未签收") ,SIGN(2,"签收"), REFUSE(1,"拒签");

    CommonOrderStatus(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    private Integer index;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}

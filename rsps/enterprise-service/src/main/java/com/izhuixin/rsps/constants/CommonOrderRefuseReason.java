package com.izhuixin.rsps.constants;

import java.io.Serializable;

public enum CommonOrderRefuseReason implements Serializable {
    NONE(0,"无"),
    DULL_OF_SALE(1,"滞销"),
    OVERSTOCKED_PRODUCTS(2,"积压"),
    RUSH_RED(3,"冲红"),
    RESUSED(4,"拒收"),
    PART_SIGN(5,"部分签收"),
    INVOICE_DISCREPANCY(6,"货单不符");

    CommonOrderRefuseReason(Integer index, String desc){
        this.index  = index;
        this.desc = desc;
    }
    private Integer index;
    private String desc;


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

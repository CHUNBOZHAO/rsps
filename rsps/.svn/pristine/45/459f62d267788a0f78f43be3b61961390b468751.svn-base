package com.izhuixin.rsps.common.constant;

/**
 * 操作人员类型
 */
public enum OperatorType {

    SUPER(0, "超级管理员"),MANAGER(1, "管理"),RECHECKER(2, "复检"),DRIVER(3, "配送员"),WAREHOUSE(4,"库房");

    private Integer index;

    private String descr;

    OperatorType(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }

    public static String getDesc(int index) {
        for (OperatorType c : OperatorType.values()) {
            if (c.getIndex() == index) {
                return c.descr;
            }
        }
        return null;
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

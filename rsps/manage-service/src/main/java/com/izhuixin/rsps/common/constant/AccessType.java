package com.izhuixin.rsps.common.constant;

/**
 * 企业数据接入类型
 */
public enum AccessType {

    ONE(1, "方式1"), TWO(2,"方式2");

    private Integer index;

    private String descr;

    AccessType(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }


    /**
     * 获取包装箱描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (AccessType c : AccessType.values()) {
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

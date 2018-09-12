package com.izhuixin.rsps.common.constant;

/**
 * 线路关联类型
 */
public enum LineRelatedType {

    CUSTOM(1, "客户"), TRANSFER_STATION(2, "中转站点");

    private Integer index;

    private String descr;

    LineRelatedType(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }


    /**
     * 获取包装箱描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (LineRelatedType c : LineRelatedType.values()) {
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

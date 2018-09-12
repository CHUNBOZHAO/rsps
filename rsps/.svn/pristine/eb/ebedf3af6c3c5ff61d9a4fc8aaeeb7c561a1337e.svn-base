package com.izhuixin.rsps.model;

/**
 * 包装箱状态
 */
public enum BoxStatus {

    LEISURE(0, "闲置"), BINDING(1, "配货"), TRANSPORTING(2, "运输"), RETENTION(3, "滞留"), RECYCLE(4, "回收"), TRANSIT(5, "中转");

    private Integer index;

    private String descr;

    BoxStatus(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }


    /**
     * 获取包装箱描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (BoxStatus c : BoxStatus.values()) {
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

package com.izhuixin.rsps.common.constant;

/**
 * 订单类型
 */
public enum OrderType {

    CONSUMER(1, "C端用户"), BUSINESS(2,"B端用户");

    private Integer index;

    private String descr;

    OrderType(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }


    /**
     * 获取包装箱描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (OrderType c : OrderType.values()) {
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

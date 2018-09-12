package com.izhuixin.rsps.common.constant;

/**
 * 订单状态
 */
public enum OrderState {

    WAITING_ASSIGN(0, "等待接单"),
    WAITING_ASSIGN_FAILED(1, "接单失败"),
    WAITING_TAKE_IN(2, "等待揽件"),
    DONE(3, "(揽件成功)已绑定包装箱"),
    COURIER_SIGNED(4, "配送员签收"),
    CUSTOMER_SIGNED(5, "客户签收"),
    WAITING_ALLOT_COURIER(6, "等待分配配送快递员")
    ;

    private Integer index;

    private String descr;

    OrderState(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }


    /**
     * 获取包装箱描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (OrderState c : OrderState.values()) {
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

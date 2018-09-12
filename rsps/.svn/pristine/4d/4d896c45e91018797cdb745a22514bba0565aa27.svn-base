package com.izhuixin.rsps.common.constant;

/**
 * 包装箱操作动作类型
 */
public enum OperateType {

    GO_HOME(0, "回库"), BINDING(1, "内复核绑定、派货"), OBTAIN(2, "收单"), SIGN_IN(3, "签收"), RECYCLE(4, "回收"), TRANSIT_SIGN_IN(5, "中转签收"), DENIED_SIGN(6, "拒签");

    private Integer index;

    private String descr;

    OperateType(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }

    /**
     * 获取包装箱描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (OperateType c : OperateType.values()) {
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

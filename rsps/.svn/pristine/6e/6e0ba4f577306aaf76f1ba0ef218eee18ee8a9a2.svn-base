package com.izhuixin.rsps.common.constant;

/**
 * 包裹签收状态
 */
public enum SignStatus {

    NOT_SIGN(0, "未签收"), SIGN_IN(1,  "已签收"), DENIED_SIGN(2, "拒签"), TRANSFER_SIGN(3, "中转签收");

    private Integer index;

    private String descr;

    SignStatus(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }

    /**
     * 获取包装箱描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (SignStatus c : SignStatus.values()) {
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

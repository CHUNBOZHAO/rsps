package com.izhuixin.rsps.common.constant;

/**
 * 回收申请消息状态
 */
public enum RecycleType {

    UN_DO(0, "未处理"), ACCEPTED(1,"已受理"), DONE(2, "处理完成");

    private Integer index;

    private String descr;

    RecycleType(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }


    /**
     * 获取包装箱描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (RecycleType c : RecycleType.values()) {
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

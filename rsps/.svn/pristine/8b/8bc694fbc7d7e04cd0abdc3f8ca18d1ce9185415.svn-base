package com.izhuixin.rsps.constant;

/**
 * 包装箱状态
 */
public enum ReportType {

    DAILY(1, "日"), WEEKLY(2, "周"), MONTHLY(3, "月"), YEARLY(4, "年");

    private Integer index;

    private String descr;

    ReportType(Integer index, String descr) {
        this.index = index;
        this.descr = descr;
    }


    /**
     * 获取报表类型描述
     * @param index
     * @return
     */
    public static String getDesc(int index) {
        for (ReportType c : ReportType.values()) {
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

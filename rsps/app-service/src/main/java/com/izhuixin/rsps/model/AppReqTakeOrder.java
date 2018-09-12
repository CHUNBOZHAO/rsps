package com.izhuixin.rsps.model;

/**
 * 揽货请求信息
 */
public class AppReqTakeOrder extends AppReqBase {

    /** 当前页 */
    private Integer pageIndex;

    /** 每页数量 */
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}

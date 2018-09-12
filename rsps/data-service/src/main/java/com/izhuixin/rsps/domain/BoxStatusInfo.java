package com.izhuixin.rsps.domain;

/**
 * 包装箱状态数量信息
 */
public class BoxStatusInfo {

    /** 包装箱总数 */
    private int total;

    /** 闲置状态数量 */
    private int leisureCount;

    /** 配货状态数量 */
    private int bindingCount;

    /** 运输状态数量 */
    private int transportingCount;

    /** 滞留状态数量 */
    private int retentionCount;

    /** 回收状态数量 */
    private int recycleCount;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLeisureCount() {
        return leisureCount;
    }

    public void setLeisureCount(int leisureCount) {
        this.leisureCount = leisureCount;
    }

    public int getBindingCount() {
        return bindingCount;
    }

    public void setBindingCount(int bindingCount) {
        this.bindingCount = bindingCount;
    }

    public int getTransportingCount() {
        return transportingCount;
    }

    public void setTransportingCount(int transportingCount) {
        this.transportingCount = transportingCount;
    }

    public int getRetentionCount() {
        return retentionCount;
    }

    public void setRetentionCount(int retentionCount) {
        this.retentionCount = retentionCount;
    }

    public int getRecycleCount() {
        return recycleCount;
    }

    public void setRecycleCount(int recycleCount) {
        this.recycleCount = recycleCount;
    }

}

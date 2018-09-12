package com.izhuixin.rsps.common.vo.app;


import java.util.List;

/**
 * 请求司机获取派单信息
 */
public class AppReqBoxes extends AppReqBase {

    /** 操作人ID  可选 */
    private Long id;

    /** 操作人ID wms提供  必选 */
    private String operatorId;

    /** 开始时间 */
    private Long beginTime;

    /** 结束时间 */
    private Long endTime;

    /** 包装箱状态 LEISURE(0, "闲置"), BINDING(1, "配货"), TRANSPORTING(2, "运输"), RETENTION(3, "滞留"), RECYCLE(4, "回收"), transit(5, "中转")*/
    private List<Byte> boxStatus;

    /** 当前页 */
    private Integer pageIndex;

    /** 每页数量 */
    private Integer pageSize;

    /** 分配方式 */
    private Integer accessType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<Byte> getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(List<Byte> boxStatus) {
        this.boxStatus = boxStatus;
    }

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

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }
}

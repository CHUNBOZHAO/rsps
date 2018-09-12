package com.izhuixin.rsps.common.vo.web;

public class BoxBaseInfoVO {

    private Integer id;
    private String rfid;
    private String type;
    private String entName;
    private String uuid;
    private String createTime;
    private String softwareVersion;
    private String hardwareVersion;
    /** 循环次数 */
    private Integer cycleIndex;

//    //使用率
//    private String usageRate;
    //最近使用箱子的时间
    private String recentUseTime;

    //通讯次数
    private String communicateCount;
    //上线时间
    private String onlineTime;
//    //周转率
//    private String revolveRate;

    //当前状态
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public Integer getCycleIndex() {
        return cycleIndex;
    }

    public void setCycleIndex(Integer cycleIndex) {
        this.cycleIndex = cycleIndex;
    }

//    public String getUsageRate() {
//        return usageRate;
//    }
//
//    public void setUsageRate(String usageRate) {
//        this.usageRate = usageRate;
//    }

    public String getRecentUseTime() {
        return recentUseTime;
    }

    public void setRecentUseTime(String recentUseTime) {
        this.recentUseTime = recentUseTime;
    }

    public String getCommunicateCount() {
        return communicateCount;
    }

    public void setCommunicateCount(String communicateCount) {
        this.communicateCount = communicateCount;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

//    public String getRevolveRate() {
//        return revolveRate;
//    }
//
//    public void setRevolveRate(String revolveRate) {
//        this.revolveRate = revolveRate;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

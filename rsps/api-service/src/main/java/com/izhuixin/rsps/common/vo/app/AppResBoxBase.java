package com.izhuixin.rsps.common.vo.app;

/**
 * 包装基本信息
 */
public class AppResBoxBase extends AppResBase {

    /** rfid */
    private String rfid;

    /** 蓝牙ID */
    private String uuid;

    /** 包装箱类型 */
    public String type;

    /** 软件版本 */
    public String softwareVersion;

    /** 硬件版本 */
    public String hardwareVersion;

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}

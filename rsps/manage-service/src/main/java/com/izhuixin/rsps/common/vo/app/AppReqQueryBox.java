package com.izhuixin.rsps.common.vo.app;

/**
 * 查询基本信息
 */
public class AppReqQueryBox extends AppReqBase {

    /** rfid */
    public String rfid;

    /** 蓝牙ID */
    public String uuid;

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

}

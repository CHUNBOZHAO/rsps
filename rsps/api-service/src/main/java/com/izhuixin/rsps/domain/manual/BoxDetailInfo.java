package com.izhuixin.rsps.domain.manual;

import java.util.Date;

public class BoxDetailInfo {

    private Integer id;
    private String boxId;

    private short opentimeLongest;
    private short closetimeLongest;
    private short opentimeLast;
    private short closetimeLast;
    private short vibrationCount;
    private short voltage;
    private byte temperature;
    private byte errcode;

    private byte brcycle;
    private byte canconnect;
    private byte caninterrupt;
    private byte brpower;
    private byte tempcycle;
    private byte volcycle;
    private byte rfidcycle;
    private byte opencheckdelay;

    //通讯次数
    private Integer communicateCount;

    private String uuid;
    //时间
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public short getOpentimeLongest() {
        return opentimeLongest;
    }

    public void setOpentimeLongest(short opentimeLongest) {
        this.opentimeLongest = opentimeLongest;
    }

    public short getClosetimeLongest() {
        return closetimeLongest;
    }

    public void setClosetimeLongest(short closetimeLongest) {
        this.closetimeLongest = closetimeLongest;
    }

    public short getOpentimeLast() {
        return opentimeLast;
    }

    public void setOpentimeLast(short opentimeLast) {
        this.opentimeLast = opentimeLast;
    }

    public short getClosetimeLast() {
        return closetimeLast;
    }

    public void setClosetimeLast(short closetimeLast) {
        this.closetimeLast = closetimeLast;
    }

    public short getVibrationCount() {
        return vibrationCount;
    }

    public void setVibrationCount(short vibrationCount) {
        this.vibrationCount = vibrationCount;
    }

    public short getVoltage() {
        return voltage;
    }

    public void setVoltage(short voltage) {
        this.voltage = voltage;
    }

    public byte getTemperature() {
        return temperature;
    }

    public void setTemperature(byte temperature) {
        this.temperature = temperature;
    }

    public byte getErrcode() {
        return errcode;
    }

    public void setErrcode(byte errcode) {
        this.errcode = errcode;
    }

    public byte getBrcycle() {
        return brcycle;
    }

    public void setBrcycle(byte brcycle) {
        this.brcycle = brcycle;
    }

    public byte getCanconnect() {
        return canconnect;
    }

    public void setCanconnect(byte canconnect) {
        this.canconnect = canconnect;
    }

    public byte getCaninterrupt() {
        return caninterrupt;
    }

    public void setCaninterrupt(byte caninterrupt) {
        this.caninterrupt = caninterrupt;
    }

    public byte getBrpower() {
        return brpower;
    }

    public void setBrpower(byte brpower) {
        this.brpower = brpower;
    }

    public byte getTempcycle() {
        return tempcycle;
    }

    public void setTempcycle(byte tempcycle) {
        this.tempcycle = tempcycle;
    }

    public byte getVolcycle() {
        return volcycle;
    }

    public void setVolcycle(byte volcycle) {
        this.volcycle = volcycle;
    }

    public byte getRfidcycle() {
        return rfidcycle;
    }

    public void setRfidcycle(byte rfidcycle) {
        this.rfidcycle = rfidcycle;
    }

    public byte getOpencheckdelay() {
        return opencheckdelay;
    }

    public void setOpencheckdelay(byte opencheckdelay) {
        this.opencheckdelay = opencheckdelay;
    }

    public Integer getCommunicateCount() {
        return communicateCount;
    }

    public void setCommunicateCount(Integer communicateCount) {
        this.communicateCount = communicateCount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

package com.izhuixin.rsps.proto;

public class Status {
    short opentimeLongest;
    short closetimeLongest;
    short opentimeLast;
    short closetimeLast;
    short vibrationCount;
    short voltage;
    byte temperature;
    byte errcode;


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
}

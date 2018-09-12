package com.izhuixin.rsps.common.vo.web;

public class BoxPara {
    String boxid;
    short communicateInterval;
    byte cycleMode;
    byte cycleTimes;
    byte boxStatus;
    byte paraUpload;
    byte statusUpload;
    byte broadcastInterval;
    byte canConnect;
    byte enableInterrupt;
    byte broadcastPower;
    byte tempInterval;
    byte voltageInterval;
    byte rfidWriteInterval;
    byte openCheckDelay;
    short vibrateThreshold;

    public String getBoxid() {
        return boxid;
    }

    public void setBoxid(String boxid) {
        this.boxid = boxid;
    }

    public short getCommunicateInterval() {
        return communicateInterval;
    }

    public void setCommunicateInterval(short communicateInterval) {
        this.communicateInterval = communicateInterval;
    }

    public byte getCycleMode() {
        return cycleMode;
    }

    public void setCycleMode(byte cycleMode) {
        this.cycleMode = cycleMode;
    }

    public byte getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(byte cycleTimes) {
        this.cycleTimes = cycleTimes;
    }

    public byte getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(byte boxStatus) {
        this.boxStatus = boxStatus;
    }

    public byte getParaUpload() {
        return paraUpload;
    }

    public void setParaUpload(byte paraUpload) {
        this.paraUpload = paraUpload;
    }

    public byte getStatusUpload() {
        return statusUpload;
    }

    public void setStatusUpload(byte statusUpload) {
        this.statusUpload = statusUpload;
    }

    public byte getBroadcastInterval() {
        return broadcastInterval;
    }

    public void setBroadcastInterval(byte broadcastInterval) {
        this.broadcastInterval = broadcastInterval;
    }

    public byte getCanConnect() {
        return canConnect;
    }

    public void setCanConnect(byte canConnect) {
        this.canConnect = canConnect;
    }

    public byte getEnableInterrupt() {
        return enableInterrupt;
    }

    public void setEnableInterrupt(byte enableInterrupt) {
        this.enableInterrupt = enableInterrupt;
    }

    public byte getBroadcastPower() {
        return broadcastPower;
    }

    public void setBroadcastPower(byte broadcastPower) {
        this.broadcastPower = broadcastPower;
    }

    public byte getTempInterval() {
        return tempInterval;
    }

    public void setTempInterval(byte tempInterval) {
        this.tempInterval = tempInterval;
    }

    public byte getVoltageInterval() {
        return voltageInterval;
    }

    public void setVoltageInterval(byte voltageInterval) {
        this.voltageInterval = voltageInterval;
    }

    public byte getRfidWriteInterval() {
        return rfidWriteInterval;
    }

    public void setRfidWriteInterval(byte rfidWriteInterval) {
        this.rfidWriteInterval = rfidWriteInterval;
    }

    public byte getOpenCheckDelay() {
        return openCheckDelay;
    }

    public void setOpenCheckDelay(byte openCheckDelay) {
        this.openCheckDelay = openCheckDelay;
    }

    public short getVibrateThreshold() {
        return vibrateThreshold;
    }

    public void setVibrateThreshold(short vibrateThreshold) {
        this.vibrateThreshold = vibrateThreshold;
    }
}

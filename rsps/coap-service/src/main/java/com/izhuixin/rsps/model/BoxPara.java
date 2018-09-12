package com.izhuixin.rsps.model;

public class BoxPara {
    String boxid;
    Short communicateInterval = null;
    Byte cycleMode;
    Byte cycleTimes;
    Byte boxStatus;
    Byte paraUpload;
    Byte statusUpload;
    Byte broadcastInterval;
    Byte canConnect;
    Byte enableInterrupt;
    Byte broadcastPower;
    Byte tempInterval;
    Byte voltageInterval;
    Byte rfidWriteInterval;
    Byte openCheckDelay;
    Short vibrateThreshold;

    public String getBoxid() {
        return boxid;
    }

    public void setBoxid(String boxid) {
        this.boxid = boxid;
    }

    public Short getCommunicateInterval() {
        return communicateInterval;
    }

    public void setCommunicateInterval(Short communicateInterval) {
        this.communicateInterval = communicateInterval;
    }

    public Byte getCycleMode() {
        return cycleMode;
    }

    public void setCycleMode(Byte cycleMode) {
        this.cycleMode = cycleMode;
    }

    public Byte getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(Byte cycleTimes) {
        this.cycleTimes = cycleTimes;
    }

    public Byte getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Byte boxStatus) {
        this.boxStatus = boxStatus;
    }

    public Byte getParaUpload() {
        return paraUpload;
    }

    public void setParaUpload(Byte paraUpload) {
        this.paraUpload = paraUpload;
    }

    public Byte getStatusUpload() {
        return statusUpload;
    }

    public void setStatusUpload(Byte statusUpload) {
        this.statusUpload = statusUpload;
    }

    public Byte getBroadcastInterval() {
        return broadcastInterval;
    }

    public void setBroadcastInterval(Byte broadcastInterval) {
        this.broadcastInterval = broadcastInterval;
    }

    public Byte getCanConnect() {
        return canConnect;
    }

    public void setCanConnect(Byte canConnect) {
        this.canConnect = canConnect;
    }

    public Byte getEnableInterrupt() {
        return enableInterrupt;
    }

    public void setEnableInterrupt(Byte enableInterrupt) {
        this.enableInterrupt = enableInterrupt;
    }

    public Byte getBroadcastPower() {
        return broadcastPower;
    }

    public void setBroadcastPower(Byte broadcastPower) {
        this.broadcastPower = broadcastPower;
    }

    public Byte getTempInterval() {
        return tempInterval;
    }

    public void setTempInterval(Byte tempInterval) {
        this.tempInterval = tempInterval;
    }

    public Byte getVoltageInterval() {
        return voltageInterval;
    }

    public void setVoltageInterval(Byte voltageInterval) {
        this.voltageInterval = voltageInterval;
    }

    public Byte getRfidWriteInterval() {
        return rfidWriteInterval;
    }

    public void setRfidWriteInterval(Byte rfidWriteInterval) {
        this.rfidWriteInterval = rfidWriteInterval;
    }

    public Byte getOpenCheckDelay() {
        return openCheckDelay;
    }

    public void setOpenCheckDelay(Byte openCheckDelay) {
        this.openCheckDelay = openCheckDelay;
    }

    public Short getVibrateThreshold() {
        return vibrateThreshold;
    }

    public void setVibrateThreshold(Short vibrateThreshold) {
        this.vibrateThreshold = vibrateThreshold;
    }
}

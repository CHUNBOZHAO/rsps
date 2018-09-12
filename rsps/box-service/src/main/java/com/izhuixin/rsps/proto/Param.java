package com.izhuixin.rsps.proto;

public class Param {
    byte brcycle;
    byte canconnect;
    byte caninterrupt;
    byte brpower;
    byte tempcycle;
    byte volcycle;
    byte rfidcycle;
    byte opencheckdelay;

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
}

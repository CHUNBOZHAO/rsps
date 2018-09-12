package com.izhuixin.rsps.model;

public class BoxCfg {
    byte type;
    byte len;
    byte[] cmd;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getLen() {
        return len;
    }

    public void setLen(byte len) {
        this.len = len;
    }

    public byte[] getCmd() {
        return cmd;
    }

    public void setCmd(byte[] cmd) {
        this.cmd = cmd;
    }
}

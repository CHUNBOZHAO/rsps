package com.izhuixin.rsps.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class BoxCmd implements Serializable {
    String boxid;
    byte type;
    byte[] cmd;

    @Id
    public String getBoxid() {
        return boxid;
    }

    public void setBoxid(String boxid) {
        this.boxid = boxid;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte[] getCmd() {
        return cmd;
    }

    public void setCmd(byte[] cmd) {
        this.cmd = cmd;
    }
}

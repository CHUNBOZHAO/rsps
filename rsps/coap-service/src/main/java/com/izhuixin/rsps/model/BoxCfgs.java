package com.izhuixin.rsps.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class BoxCfgs {
    String boxid;
    ArrayList<BoxCfg> boxCfgs;

    @Id
    public String getBoxid() {
        return boxid;
    }

    public void setBoxid(String boxid) {
        this.boxid = boxid;
    }

    public ArrayList<BoxCfg> getBoxCfgs() {
        return boxCfgs;
    }

    public void setBoxCfgs(ArrayList<BoxCfg> boxCfgs) {
        this.boxCfgs = boxCfgs;
    }
}

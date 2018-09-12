package com.izhuixin.rsps.proto;

public class Cell {
    int lac;
    int cellid;
    short mciss;

    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getCellid() {
        return cellid;
    }

    public void setCellid(int cellid) {
        this.cellid = cellid;
    }

    public short getMciss() {
        return mciss;
    }

    public void setMciss(short mciss) {
        this.mciss = mciss;
    }
}

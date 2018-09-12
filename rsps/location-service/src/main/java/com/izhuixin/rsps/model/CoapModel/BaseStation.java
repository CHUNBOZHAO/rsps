package com.izhuixin.rsps.model.CoapModel;

public class BaseStation {
    private String  ID;
    private Integer AC;
    private Integer CI;

    private Integer Radius;
    private double Lata;
    private double Lnga;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getAC() {
        return AC;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }

    public int getCI() {
        return CI;
    }

    public void setCI(int CI) {
        this.CI = CI;
    }

    public Integer getRadius() {
        return Radius;
    }

    public void setRadius(Integer radius) {
        Radius = radius;
    }

    public double getLata() {
        return Lata;
    }

    public void setLata(double lata) {
        Lata = lata;
    }

    public double getLnga() {
        return Lnga;
    }

    public void setLnga(double lnga) {
        Lnga = lnga;
    }
}

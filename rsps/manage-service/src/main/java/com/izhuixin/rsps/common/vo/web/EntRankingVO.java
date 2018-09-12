package com.izhuixin.rsps.common.vo.web;

public class EntRankingVO {

    private String entId;
    private String entName;
    private double rate;
    private String rateVO;

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getRateVO() {
        return rateVO;
    }

    public void setRateVO(String rateVO) {
        this.rateVO = rateVO;
    }

    public EntRankingVO(String entId, String entName, double rate, String rateVO) {
        this.entId = entId;
        this.entName = entName;
        this.rate = rate;
        this.rateVO = rateVO;
    }
    public EntRankingVO() {
    }
}

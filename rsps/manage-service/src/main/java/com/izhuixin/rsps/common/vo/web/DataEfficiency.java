package com.izhuixin.rsps.common.vo.web;

import java.util.Date;

public class DataEfficiency {

    private Integer id;
    private String entId;
    private String type;
    private Date generateDate;
    private double idleRate;
    private double turnoverRate;
    private double loseRate;
    private double overdueRate;
    private double other1Rate;
    private double other2Rate;
    private double other3Rate;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    public double getIdleRate() {
        return idleRate;
    }

    public void setIdleRate(double idleRate) {
        this.idleRate = idleRate;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public double getLoseRate() {
        return loseRate;
    }

    public void setLoseRate(double loseRate) {
        this.loseRate = loseRate;
    }

    public double getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(double overdueRate) {
        this.overdueRate = overdueRate;
    }

    public double getOther1Rate() {
        return other1Rate;
    }

    public void setOther1Rate(double other1Rate) {
        this.other1Rate = other1Rate;
    }

    public double getOther2Rate() {
        return other2Rate;
    }

    public void setOther2Rate(double other2Rate) {
        this.other2Rate = other2Rate;
    }

    public double getOther3Rate() {
        return other3Rate;
    }

    public void setOther3Rate(double other3Rate) {
        this.other3Rate = other3Rate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

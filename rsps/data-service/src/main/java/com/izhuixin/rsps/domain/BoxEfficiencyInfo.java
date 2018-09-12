package com.izhuixin.rsps.domain;

import java.util.Date;

/**
 * 包装箱效率统计
 */
public class BoxEfficiencyInfo {

    /** ID */
    private Integer id;

    /** 企业ID */
    private String entId;

    /** 类型 1: 日 2: 周 3: 月 4: 年 */
    private Byte type;

    /** 数据时间 */
    private Date generateDate;

    /** 闲置率 */
    private Float idleRate;

    /** 周转率 */
    private Float turnoverRate;

    /** 遗失率 */
    private Float loseRate;

    /** 过期率 */
    private Float overdueRate;

    /** 备用1 */
    private Float other1Rate;

    /** 备用2 */
    private Float other2Rate;

    /** 备用3 */
    private Float other3Rate;

    /** 创建时间 */
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    public Float getIdleRate() {
        return idleRate;
    }

    public void setIdleRate(Float idleRate) {
        this.idleRate = idleRate;
    }

    public Float getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Float turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Float getLoseRate() {
        return loseRate;
    }

    public void setLoseRate(Float loseRate) {
        this.loseRate = loseRate;
    }

    public Float getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(Float overdueRate) {
        this.overdueRate = overdueRate;
    }

    public Float getOther1Rate() {
        return other1Rate;
    }

    public void setOther1Rate(Float other1Rate) {
        this.other1Rate = other1Rate;
    }

    public Float getOther2Rate() {
        return other2Rate;
    }

    public void setOther2Rate(Float other2Rate) {
        this.other2Rate = other2Rate;
    }

    public Float getOther3Rate() {
        return other3Rate;
    }

    public void setOther3Rate(Float other3Rate) {
        this.other3Rate = other3Rate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

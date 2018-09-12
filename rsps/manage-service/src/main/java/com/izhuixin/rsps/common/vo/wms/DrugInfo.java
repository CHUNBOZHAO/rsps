package com.izhuixin.rsps.common.vo.wms;

/**
 * 药品信息
 */
public class DrugInfo {

    /** 药品名称 */
    private String name;

    /** 药品数量 */
    private String amount;

    /** 生产厂家 */
    private String manufacturer;

    /** 单位 */
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

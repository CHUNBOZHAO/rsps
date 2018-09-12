package com.izhuixin.rsps.common.vo.web;

import java.io.Serializable;

/**
 * 包装箱类型
 */
public class BoxTypeVO implements Serializable {

    /** Type ID */
    private String typeId;

    /** 名称 */
    private String name;

    /** 包装箱颜色 */
    private String color;

    /** 附加属性：包装箱大小 */
    private String size;

    /** 附加属性：包装箱数量 */
    private Integer count;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

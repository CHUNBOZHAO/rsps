package com.izhuixin.rsps.common.vo.web;

import java.io.Serializable;

/**
 * 客户占用包装箱数量
 */
public class BoxesCustomInfoVO implements Serializable{

    /** 客户ID */
    private String customId;

    private String customName;

    /** 目标客户ID 目前用户C端， B端和customId一致 */
    private String customShowId;

    private String customShowName;

    private Long count;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCustomShowId() {
        return customShowId;
    }

    public void setCustomShowId(String customShowId) {
        this.customShowId = customShowId;
    }

    public String getCustomShowName() {
        return customShowName;
    }

    public void setCustomShowName(String customShowName) {
        this.customShowName = customShowName;
    }
}

package com.izhuixin.rsps.common.dao;

import java.io.Serializable;

/**
 * 操作人负责包装箱数量信息
 */
public class BoxesOperatorInfo implements Serializable {

    private String operatorId;

    private String operator;

    private Long count;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

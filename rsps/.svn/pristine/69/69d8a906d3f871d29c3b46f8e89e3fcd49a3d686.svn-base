package com.izhuixin.rsps.domain.automatic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemParamCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemParamCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNull() {
            addCriterion("param_name is null");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNotNull() {
            addCriterion("param_name is not null");
            return (Criteria) this;
        }

        public Criteria andParamNameEqualTo(String value) {
            addCriterion("param_name =", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotEqualTo(String value) {
            addCriterion("param_name <>", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThan(String value) {
            addCriterion("param_name >", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThanOrEqualTo(String value) {
            addCriterion("param_name >=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThan(String value) {
            addCriterion("param_name <", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThanOrEqualTo(String value) {
            addCriterion("param_name <=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLike(String value) {
            addCriterion("param_name like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotLike(String value) {
            addCriterion("param_name not like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameIn(List<String> values) {
            addCriterion("param_name in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotIn(List<String> values) {
            addCriterion("param_name not in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameBetween(String value1, String value2) {
            addCriterion("param_name between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotBetween(String value1, String value2) {
            addCriterion("param_name not between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamValueIsNull() {
            addCriterion("param_value is null");
            return (Criteria) this;
        }

        public Criteria andParamValueIsNotNull() {
            addCriterion("param_value is not null");
            return (Criteria) this;
        }

        public Criteria andParamValueEqualTo(String value) {
            addCriterion("param_value =", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotEqualTo(String value) {
            addCriterion("param_value <>", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueGreaterThan(String value) {
            addCriterion("param_value >", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueGreaterThanOrEqualTo(String value) {
            addCriterion("param_value >=", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLessThan(String value) {
            addCriterion("param_value <", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLessThanOrEqualTo(String value) {
            addCriterion("param_value <=", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLike(String value) {
            addCriterion("param_value like", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotLike(String value) {
            addCriterion("param_value not like", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueIn(List<String> values) {
            addCriterion("param_value in", values, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotIn(List<String> values) {
            addCriterion("param_value not in", values, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueBetween(String value1, String value2) {
            addCriterion("param_value between", value1, value2, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotBetween(String value1, String value2) {
            addCriterion("param_value not between", value1, value2, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamDescrIsNull() {
            addCriterion("param_descr is null");
            return (Criteria) this;
        }

        public Criteria andParamDescrIsNotNull() {
            addCriterion("param_descr is not null");
            return (Criteria) this;
        }

        public Criteria andParamDescrEqualTo(String value) {
            addCriterion("param_descr =", value, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrNotEqualTo(String value) {
            addCriterion("param_descr <>", value, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrGreaterThan(String value) {
            addCriterion("param_descr >", value, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrGreaterThanOrEqualTo(String value) {
            addCriterion("param_descr >=", value, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrLessThan(String value) {
            addCriterion("param_descr <", value, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrLessThanOrEqualTo(String value) {
            addCriterion("param_descr <=", value, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrLike(String value) {
            addCriterion("param_descr like", value, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrNotLike(String value) {
            addCriterion("param_descr not like", value, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrIn(List<String> values) {
            addCriterion("param_descr in", values, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrNotIn(List<String> values) {
            addCriterion("param_descr not in", values, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrBetween(String value1, String value2) {
            addCriterion("param_descr between", value1, value2, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamDescrNotBetween(String value1, String value2) {
            addCriterion("param_descr not between", value1, value2, "paramDescr");
            return (Criteria) this;
        }

        public Criteria andParamStatusIsNull() {
            addCriterion("param_status is null");
            return (Criteria) this;
        }

        public Criteria andParamStatusIsNotNull() {
            addCriterion("param_status is not null");
            return (Criteria) this;
        }

        public Criteria andParamStatusEqualTo(Byte value) {
            addCriterion("param_status =", value, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusNotEqualTo(Byte value) {
            addCriterion("param_status <>", value, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusGreaterThan(Byte value) {
            addCriterion("param_status >", value, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("param_status >=", value, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusLessThan(Byte value) {
            addCriterion("param_status <", value, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusLessThanOrEqualTo(Byte value) {
            addCriterion("param_status <=", value, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusIn(List<Byte> values) {
            addCriterion("param_status in", values, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusNotIn(List<Byte> values) {
            addCriterion("param_status not in", values, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusBetween(Byte value1, Byte value2) {
            addCriterion("param_status between", value1, value2, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andParamStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("param_status not between", value1, value2, "paramStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Integer value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Integer value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Integer value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Integer value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Integer> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Integer> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andParamRemarkIsNull() {
            addCriterion("param_remark is null");
            return (Criteria) this;
        }

        public Criteria andParamRemarkIsNotNull() {
            addCriterion("param_remark is not null");
            return (Criteria) this;
        }

        public Criteria andParamRemarkEqualTo(String value) {
            addCriterion("param_remark =", value, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkNotEqualTo(String value) {
            addCriterion("param_remark <>", value, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkGreaterThan(String value) {
            addCriterion("param_remark >", value, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("param_remark >=", value, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkLessThan(String value) {
            addCriterion("param_remark <", value, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkLessThanOrEqualTo(String value) {
            addCriterion("param_remark <=", value, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkLike(String value) {
            addCriterion("param_remark like", value, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkNotLike(String value) {
            addCriterion("param_remark not like", value, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkIn(List<String> values) {
            addCriterion("param_remark in", values, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkNotIn(List<String> values) {
            addCriterion("param_remark not in", values, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkBetween(String value1, String value2) {
            addCriterion("param_remark between", value1, value2, "paramRemark");
            return (Criteria) this;
        }

        public Criteria andParamRemarkNotBetween(String value1, String value2) {
            addCriterion("param_remark not between", value1, value2, "paramRemark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
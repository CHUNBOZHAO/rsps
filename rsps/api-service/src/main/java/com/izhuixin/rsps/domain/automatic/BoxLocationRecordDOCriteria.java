package com.izhuixin.rsps.domain.automatic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoxLocationRecordDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BoxLocationRecordDOCriteria() {
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

        public Criteria andRfidIsNull() {
            addCriterion("rfid is null");
            return (Criteria) this;
        }

        public Criteria andRfidIsNotNull() {
            addCriterion("rfid is not null");
            return (Criteria) this;
        }

        public Criteria andRfidEqualTo(String value) {
            addCriterion("rfid =", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotEqualTo(String value) {
            addCriterion("rfid <>", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidGreaterThan(String value) {
            addCriterion("rfid >", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidGreaterThanOrEqualTo(String value) {
            addCriterion("rfid >=", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLessThan(String value) {
            addCriterion("rfid <", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLessThanOrEqualTo(String value) {
            addCriterion("rfid <=", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLike(String value) {
            addCriterion("rfid like", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotLike(String value) {
            addCriterion("rfid not like", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidIn(List<String> values) {
            addCriterion("rfid in", values, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotIn(List<String> values) {
            addCriterion("rfid not in", values, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidBetween(String value1, String value2) {
            addCriterion("rfid between", value1, value2, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotBetween(String value1, String value2) {
            addCriterion("rfid not between", value1, value2, "rfid");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andDuAddressIsNull() {
            addCriterion("du_address is null");
            return (Criteria) this;
        }

        public Criteria andDuAddressIsNotNull() {
            addCriterion("du_address is not null");
            return (Criteria) this;
        }

        public Criteria andDuAddressEqualTo(String value) {
            addCriterion("du_address =", value, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressNotEqualTo(String value) {
            addCriterion("du_address <>", value, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressGreaterThan(String value) {
            addCriterion("du_address >", value, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressGreaterThanOrEqualTo(String value) {
            addCriterion("du_address >=", value, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressLessThan(String value) {
            addCriterion("du_address <", value, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressLessThanOrEqualTo(String value) {
            addCriterion("du_address <=", value, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressLike(String value) {
            addCriterion("du_address like", value, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressNotLike(String value) {
            addCriterion("du_address not like", value, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressIn(List<String> values) {
            addCriterion("du_address in", values, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressNotIn(List<String> values) {
            addCriterion("du_address not in", values, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressBetween(String value1, String value2) {
            addCriterion("du_address between", value1, value2, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuAddressNotBetween(String value1, String value2) {
            addCriterion("du_address not between", value1, value2, "duAddress");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeIsNull() {
            addCriterion("du_latitude is null");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeIsNotNull() {
            addCriterion("du_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeEqualTo(Double value) {
            addCriterion("du_latitude =", value, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeNotEqualTo(Double value) {
            addCriterion("du_latitude <>", value, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeGreaterThan(Double value) {
            addCriterion("du_latitude >", value, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("du_latitude >=", value, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeLessThan(Double value) {
            addCriterion("du_latitude <", value, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("du_latitude <=", value, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeIn(List<Double> values) {
            addCriterion("du_latitude in", values, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeNotIn(List<Double> values) {
            addCriterion("du_latitude not in", values, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeBetween(Double value1, Double value2) {
            addCriterion("du_latitude between", value1, value2, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("du_latitude not between", value1, value2, "duLatitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeIsNull() {
            addCriterion("du_longitude is null");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeIsNotNull() {
            addCriterion("du_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeEqualTo(Double value) {
            addCriterion("du_longitude =", value, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeNotEqualTo(Double value) {
            addCriterion("du_longitude <>", value, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeGreaterThan(Double value) {
            addCriterion("du_longitude >", value, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("du_longitude >=", value, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeLessThan(Double value) {
            addCriterion("du_longitude <", value, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("du_longitude <=", value, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeIn(List<Double> values) {
            addCriterion("du_longitude in", values, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeNotIn(List<Double> values) {
            addCriterion("du_longitude not in", values, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeBetween(Double value1, Double value2) {
            addCriterion("du_longitude between", value1, value2, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("du_longitude not between", value1, value2, "duLongitude");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeIsNull() {
            addCriterion("du_coord_type is null");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeIsNotNull() {
            addCriterion("du_coord_type is not null");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeEqualTo(Byte value) {
            addCriterion("du_coord_type =", value, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeNotEqualTo(Byte value) {
            addCriterion("du_coord_type <>", value, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeGreaterThan(Byte value) {
            addCriterion("du_coord_type >", value, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("du_coord_type >=", value, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeLessThan(Byte value) {
            addCriterion("du_coord_type <", value, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeLessThanOrEqualTo(Byte value) {
            addCriterion("du_coord_type <=", value, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeIn(List<Byte> values) {
            addCriterion("du_coord_type in", values, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeNotIn(List<Byte> values) {
            addCriterion("du_coord_type not in", values, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeBetween(Byte value1, Byte value2) {
            addCriterion("du_coord_type between", value1, value2, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andDuCoordTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("du_coord_type not between", value1, value2, "duCoordType");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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
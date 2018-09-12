package com.izhuixin.rsps.domain.automatic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoxInfoDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BoxInfoDOCriteria() {
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

        public Criteria andBarcodeIsNull() {
            addCriterion("barcode is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("barcode is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("barcode =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("barcode <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("barcode >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("barcode >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("barcode <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("barcode <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("barcode like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("barcode not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("barcode in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("barcode not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("barcode between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("barcode not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andOperatorIdEqualTo(String value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(String value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(String value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(String value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLike(String value) {
            addCriterion("operator_id like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotLike(String value) {
            addCriterion("operator_id not like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<String> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<String> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(String value1, String value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(String value1, String value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNull() {
            addCriterion("operate_time is null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNotNull() {
            addCriterion("operate_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeEqualTo(Date value) {
            addCriterion("operate_time =", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotEqualTo(Date value) {
            addCriterion("operate_time <>", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThan(Date value) {
            addCriterion("operate_time >", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operate_time >=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThan(Date value) {
            addCriterion("operate_time <", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThanOrEqualTo(Date value) {
            addCriterion("operate_time <=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIn(List<Date> values) {
            addCriterion("operate_time in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotIn(List<Date> values) {
            addCriterion("operate_time not in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeBetween(Date value1, Date value2) {
            addCriterion("operate_time between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotBetween(Date value1, Date value2) {
            addCriterion("operate_time not between", value1, value2, "operateTime");
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

        public Criteria andDuTableIdIsNull() {
            addCriterion("du_table_id is null");
            return (Criteria) this;
        }

        public Criteria andDuTableIdIsNotNull() {
            addCriterion("du_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andDuTableIdEqualTo(String value) {
            addCriterion("du_table_id =", value, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdNotEqualTo(String value) {
            addCriterion("du_table_id <>", value, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdGreaterThan(String value) {
            addCriterion("du_table_id >", value, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdGreaterThanOrEqualTo(String value) {
            addCriterion("du_table_id >=", value, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdLessThan(String value) {
            addCriterion("du_table_id <", value, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdLessThanOrEqualTo(String value) {
            addCriterion("du_table_id <=", value, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdLike(String value) {
            addCriterion("du_table_id like", value, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdNotLike(String value) {
            addCriterion("du_table_id not like", value, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdIn(List<String> values) {
            addCriterion("du_table_id in", values, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdNotIn(List<String> values) {
            addCriterion("du_table_id not in", values, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdBetween(String value1, String value2) {
            addCriterion("du_table_id between", value1, value2, "duTableId");
            return (Criteria) this;
        }

        public Criteria andDuTableIdNotBetween(String value1, String value2) {
            addCriterion("du_table_id not between", value1, value2, "duTableId");
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

        public Criteria andBoxNameIsNull() {
            addCriterion("box_name is null");
            return (Criteria) this;
        }

        public Criteria andBoxNameIsNotNull() {
            addCriterion("box_name is not null");
            return (Criteria) this;
        }

        public Criteria andBoxNameEqualTo(String value) {
            addCriterion("box_name =", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotEqualTo(String value) {
            addCriterion("box_name <>", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameGreaterThan(String value) {
            addCriterion("box_name >", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameGreaterThanOrEqualTo(String value) {
            addCriterion("box_name >=", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLessThan(String value) {
            addCriterion("box_name <", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLessThanOrEqualTo(String value) {
            addCriterion("box_name <=", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLike(String value) {
            addCriterion("box_name like", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotLike(String value) {
            addCriterion("box_name not like", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameIn(List<String> values) {
            addCriterion("box_name in", values, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotIn(List<String> values) {
            addCriterion("box_name not in", values, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameBetween(String value1, String value2) {
            addCriterion("box_name between", value1, value2, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotBetween(String value1, String value2) {
            addCriterion("box_name not between", value1, value2, "boxName");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdIsNull() {
            addCriterion("next_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdIsNotNull() {
            addCriterion("next_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdEqualTo(String value) {
            addCriterion("next_operator_id =", value, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdNotEqualTo(String value) {
            addCriterion("next_operator_id <>", value, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdGreaterThan(String value) {
            addCriterion("next_operator_id >", value, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("next_operator_id >=", value, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdLessThan(String value) {
            addCriterion("next_operator_id <", value, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("next_operator_id <=", value, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdLike(String value) {
            addCriterion("next_operator_id like", value, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdNotLike(String value) {
            addCriterion("next_operator_id not like", value, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdIn(List<String> values) {
            addCriterion("next_operator_id in", values, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdNotIn(List<String> values) {
            addCriterion("next_operator_id not in", values, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdBetween(String value1, String value2) {
            addCriterion("next_operator_id between", value1, value2, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andNextOperatorIdNotBetween(String value1, String value2) {
            addCriterion("next_operator_id not between", value1, value2, "nextOperatorId");
            return (Criteria) this;
        }

        public Criteria andBindTimeIsNull() {
            addCriterion("bind_time is null");
            return (Criteria) this;
        }

        public Criteria andBindTimeIsNotNull() {
            addCriterion("bind_time is not null");
            return (Criteria) this;
        }

        public Criteria andBindTimeEqualTo(Date value) {
            addCriterion("bind_time =", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotEqualTo(Date value) {
            addCriterion("bind_time <>", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeGreaterThan(Date value) {
            addCriterion("bind_time >", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bind_time >=", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeLessThan(Date value) {
            addCriterion("bind_time <", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeLessThanOrEqualTo(Date value) {
            addCriterion("bind_time <=", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeIn(List<Date> values) {
            addCriterion("bind_time in", values, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotIn(List<Date> values) {
            addCriterion("bind_time not in", values, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeBetween(Date value1, Date value2) {
            addCriterion("bind_time between", value1, value2, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotBetween(Date value1, Date value2) {
            addCriterion("bind_time not between", value1, value2, "bindTime");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andSignStatusIsNull() {
            addCriterion("sign_status is null");
            return (Criteria) this;
        }

        public Criteria andSignStatusIsNotNull() {
            addCriterion("sign_status is not null");
            return (Criteria) this;
        }

        public Criteria andSignStatusEqualTo(Byte value) {
            addCriterion("sign_status =", value, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusNotEqualTo(Byte value) {
            addCriterion("sign_status <>", value, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusGreaterThan(Byte value) {
            addCriterion("sign_status >", value, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("sign_status >=", value, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusLessThan(Byte value) {
            addCriterion("sign_status <", value, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusLessThanOrEqualTo(Byte value) {
            addCriterion("sign_status <=", value, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusIn(List<Byte> values) {
            addCriterion("sign_status in", values, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusNotIn(List<Byte> values) {
            addCriterion("sign_status not in", values, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusBetween(Byte value1, Byte value2) {
            addCriterion("sign_status between", value1, value2, "signStatus");
            return (Criteria) this;
        }

        public Criteria andSignStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("sign_status not between", value1, value2, "signStatus");
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
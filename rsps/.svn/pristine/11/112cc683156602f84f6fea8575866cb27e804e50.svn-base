package com.izhuixin.rsps.domain.automatic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnterpriseCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnterpriseCriteria() {
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserPwdIsNull() {
            addCriterion("user_pwd is null");
            return (Criteria) this;
        }

        public Criteria andUserPwdIsNotNull() {
            addCriterion("user_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andUserPwdEqualTo(String value) {
            addCriterion("user_pwd =", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdNotEqualTo(String value) {
            addCriterion("user_pwd <>", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdGreaterThan(String value) {
            addCriterion("user_pwd >", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdGreaterThanOrEqualTo(String value) {
            addCriterion("user_pwd >=", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdLessThan(String value) {
            addCriterion("user_pwd <", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdLessThanOrEqualTo(String value) {
            addCriterion("user_pwd <=", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdLike(String value) {
            addCriterion("user_pwd like", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdNotLike(String value) {
            addCriterion("user_pwd not like", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdIn(List<String> values) {
            addCriterion("user_pwd in", values, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdNotIn(List<String> values) {
            addCriterion("user_pwd not in", values, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdBetween(String value1, String value2) {
            addCriterion("user_pwd between", value1, value2, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdNotBetween(String value1, String value2) {
            addCriterion("user_pwd not between", value1, value2, "userPwd");
            return (Criteria) this;
        }

        public Criteria andEntIdIsNull() {
            addCriterion("ent_id is null");
            return (Criteria) this;
        }

        public Criteria andEntIdIsNotNull() {
            addCriterion("ent_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntIdEqualTo(String value) {
            addCriterion("ent_id =", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotEqualTo(String value) {
            addCriterion("ent_id <>", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdGreaterThan(String value) {
            addCriterion("ent_id >", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdGreaterThanOrEqualTo(String value) {
            addCriterion("ent_id >=", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdLessThan(String value) {
            addCriterion("ent_id <", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdLessThanOrEqualTo(String value) {
            addCriterion("ent_id <=", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdLike(String value) {
            addCriterion("ent_id like", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotLike(String value) {
            addCriterion("ent_id not like", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdIn(List<String> values) {
            addCriterion("ent_id in", values, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotIn(List<String> values) {
            addCriterion("ent_id not in", values, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdBetween(String value1, String value2) {
            addCriterion("ent_id between", value1, value2, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotBetween(String value1, String value2) {
            addCriterion("ent_id not between", value1, value2, "entId");
            return (Criteria) this;
        }

        public Criteria andEntNameIsNull() {
            addCriterion("ent_name is null");
            return (Criteria) this;
        }

        public Criteria andEntNameIsNotNull() {
            addCriterion("ent_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntNameEqualTo(String value) {
            addCriterion("ent_name =", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameNotEqualTo(String value) {
            addCriterion("ent_name <>", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameGreaterThan(String value) {
            addCriterion("ent_name >", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameGreaterThanOrEqualTo(String value) {
            addCriterion("ent_name >=", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameLessThan(String value) {
            addCriterion("ent_name <", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameLessThanOrEqualTo(String value) {
            addCriterion("ent_name <=", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameLike(String value) {
            addCriterion("ent_name like", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameNotLike(String value) {
            addCriterion("ent_name not like", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameIn(List<String> values) {
            addCriterion("ent_name in", values, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameNotIn(List<String> values) {
            addCriterion("ent_name not in", values, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameBetween(String value1, String value2) {
            addCriterion("ent_name between", value1, value2, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameNotBetween(String value1, String value2) {
            addCriterion("ent_name not between", value1, value2, "entName");
            return (Criteria) this;
        }

        public Criteria andEntAddressIsNull() {
            addCriterion("ent_address is null");
            return (Criteria) this;
        }

        public Criteria andEntAddressIsNotNull() {
            addCriterion("ent_address is not null");
            return (Criteria) this;
        }

        public Criteria andEntAddressEqualTo(String value) {
            addCriterion("ent_address =", value, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressNotEqualTo(String value) {
            addCriterion("ent_address <>", value, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressGreaterThan(String value) {
            addCriterion("ent_address >", value, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ent_address >=", value, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressLessThan(String value) {
            addCriterion("ent_address <", value, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressLessThanOrEqualTo(String value) {
            addCriterion("ent_address <=", value, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressLike(String value) {
            addCriterion("ent_address like", value, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressNotLike(String value) {
            addCriterion("ent_address not like", value, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressIn(List<String> values) {
            addCriterion("ent_address in", values, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressNotIn(List<String> values) {
            addCriterion("ent_address not in", values, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressBetween(String value1, String value2) {
            addCriterion("ent_address between", value1, value2, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntAddressNotBetween(String value1, String value2) {
            addCriterion("ent_address not between", value1, value2, "entAddress");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeIsNull() {
            addCriterion("ent_postcode is null");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeIsNotNull() {
            addCriterion("ent_postcode is not null");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeEqualTo(String value) {
            addCriterion("ent_postcode =", value, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeNotEqualTo(String value) {
            addCriterion("ent_postcode <>", value, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeGreaterThan(String value) {
            addCriterion("ent_postcode >", value, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("ent_postcode >=", value, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeLessThan(String value) {
            addCriterion("ent_postcode <", value, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeLessThanOrEqualTo(String value) {
            addCriterion("ent_postcode <=", value, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeLike(String value) {
            addCriterion("ent_postcode like", value, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeNotLike(String value) {
            addCriterion("ent_postcode not like", value, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeIn(List<String> values) {
            addCriterion("ent_postcode in", values, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeNotIn(List<String> values) {
            addCriterion("ent_postcode not in", values, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeBetween(String value1, String value2) {
            addCriterion("ent_postcode between", value1, value2, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntPostcodeNotBetween(String value1, String value2) {
            addCriterion("ent_postcode not between", value1, value2, "entPostcode");
            return (Criteria) this;
        }

        public Criteria andEntCodeIsNull() {
            addCriterion("ent_code is null");
            return (Criteria) this;
        }

        public Criteria andEntCodeIsNotNull() {
            addCriterion("ent_code is not null");
            return (Criteria) this;
        }

        public Criteria andEntCodeEqualTo(String value) {
            addCriterion("ent_code =", value, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeNotEqualTo(String value) {
            addCriterion("ent_code <>", value, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeGreaterThan(String value) {
            addCriterion("ent_code >", value, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ent_code >=", value, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeLessThan(String value) {
            addCriterion("ent_code <", value, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeLessThanOrEqualTo(String value) {
            addCriterion("ent_code <=", value, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeLike(String value) {
            addCriterion("ent_code like", value, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeNotLike(String value) {
            addCriterion("ent_code not like", value, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeIn(List<String> values) {
            addCriterion("ent_code in", values, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeNotIn(List<String> values) {
            addCriterion("ent_code not in", values, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeBetween(String value1, String value2) {
            addCriterion("ent_code between", value1, value2, "entCode");
            return (Criteria) this;
        }

        public Criteria andEntCodeNotBetween(String value1, String value2) {
            addCriterion("ent_code not between", value1, value2, "entCode");
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

        public Criteria andKeyIdIsNull() {
            addCriterion("key_id is null");
            return (Criteria) this;
        }

        public Criteria andKeyIdIsNotNull() {
            addCriterion("key_id is not null");
            return (Criteria) this;
        }

        public Criteria andKeyIdEqualTo(String value) {
            addCriterion("key_id =", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdNotEqualTo(String value) {
            addCriterion("key_id <>", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdGreaterThan(String value) {
            addCriterion("key_id >", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdGreaterThanOrEqualTo(String value) {
            addCriterion("key_id >=", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdLessThan(String value) {
            addCriterion("key_id <", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdLessThanOrEqualTo(String value) {
            addCriterion("key_id <=", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdLike(String value) {
            addCriterion("key_id like", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdNotLike(String value) {
            addCriterion("key_id not like", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdIn(List<String> values) {
            addCriterion("key_id in", values, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdNotIn(List<String> values) {
            addCriterion("key_id not in", values, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdBetween(String value1, String value2) {
            addCriterion("key_id between", value1, value2, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdNotBetween(String value1, String value2) {
            addCriterion("key_id not between", value1, value2, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeySecretIsNull() {
            addCriterion("key_secret is null");
            return (Criteria) this;
        }

        public Criteria andKeySecretIsNotNull() {
            addCriterion("key_secret is not null");
            return (Criteria) this;
        }

        public Criteria andKeySecretEqualTo(String value) {
            addCriterion("key_secret =", value, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretNotEqualTo(String value) {
            addCriterion("key_secret <>", value, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretGreaterThan(String value) {
            addCriterion("key_secret >", value, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretGreaterThanOrEqualTo(String value) {
            addCriterion("key_secret >=", value, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretLessThan(String value) {
            addCriterion("key_secret <", value, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretLessThanOrEqualTo(String value) {
            addCriterion("key_secret <=", value, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretLike(String value) {
            addCriterion("key_secret like", value, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretNotLike(String value) {
            addCriterion("key_secret not like", value, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretIn(List<String> values) {
            addCriterion("key_secret in", values, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretNotIn(List<String> values) {
            addCriterion("key_secret not in", values, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretBetween(String value1, String value2) {
            addCriterion("key_secret between", value1, value2, "keySecret");
            return (Criteria) this;
        }

        public Criteria andKeySecretNotBetween(String value1, String value2) {
            addCriterion("key_secret not between", value1, value2, "keySecret");
            return (Criteria) this;
        }

        public Criteria andAkIsNull() {
            addCriterion("ak is null");
            return (Criteria) this;
        }

        public Criteria andAkIsNotNull() {
            addCriterion("ak is not null");
            return (Criteria) this;
        }

        public Criteria andAkEqualTo(String value) {
            addCriterion("ak =", value, "ak");
            return (Criteria) this;
        }

        public Criteria andAkNotEqualTo(String value) {
            addCriterion("ak <>", value, "ak");
            return (Criteria) this;
        }

        public Criteria andAkGreaterThan(String value) {
            addCriterion("ak >", value, "ak");
            return (Criteria) this;
        }

        public Criteria andAkGreaterThanOrEqualTo(String value) {
            addCriterion("ak >=", value, "ak");
            return (Criteria) this;
        }

        public Criteria andAkLessThan(String value) {
            addCriterion("ak <", value, "ak");
            return (Criteria) this;
        }

        public Criteria andAkLessThanOrEqualTo(String value) {
            addCriterion("ak <=", value, "ak");
            return (Criteria) this;
        }

        public Criteria andAkLike(String value) {
            addCriterion("ak like", value, "ak");
            return (Criteria) this;
        }

        public Criteria andAkNotLike(String value) {
            addCriterion("ak not like", value, "ak");
            return (Criteria) this;
        }

        public Criteria andAkIn(List<String> values) {
            addCriterion("ak in", values, "ak");
            return (Criteria) this;
        }

        public Criteria andAkNotIn(List<String> values) {
            addCriterion("ak not in", values, "ak");
            return (Criteria) this;
        }

        public Criteria andAkBetween(String value1, String value2) {
            addCriterion("ak between", value1, value2, "ak");
            return (Criteria) this;
        }

        public Criteria andAkNotBetween(String value1, String value2) {
            addCriterion("ak not between", value1, value2, "ak");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andAccessWayIsNull() {
            addCriterion("access_way is null");
            return (Criteria) this;
        }

        public Criteria andAccessWayIsNotNull() {
            addCriterion("access_way is not null");
            return (Criteria) this;
        }

        public Criteria andAccessWayEqualTo(Byte value) {
            addCriterion("access_way =", value, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayNotEqualTo(Byte value) {
            addCriterion("access_way <>", value, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayGreaterThan(Byte value) {
            addCriterion("access_way >", value, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("access_way >=", value, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayLessThan(Byte value) {
            addCriterion("access_way <", value, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayLessThanOrEqualTo(Byte value) {
            addCriterion("access_way <=", value, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayIn(List<Byte> values) {
            addCriterion("access_way in", values, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayNotIn(List<Byte> values) {
            addCriterion("access_way not in", values, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayBetween(Byte value1, Byte value2) {
            addCriterion("access_way between", value1, value2, "accessWay");
            return (Criteria) this;
        }

        public Criteria andAccessWayNotBetween(Byte value1, Byte value2) {
            addCriterion("access_way not between", value1, value2, "accessWay");
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
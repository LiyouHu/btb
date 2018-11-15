package com.btb.pojo;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDetailsExample() {
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

        public Criteria andOdidIsNull() {
            addCriterion("odid is null");
            return (Criteria) this;
        }

        public Criteria andOdidIsNotNull() {
            addCriterion("odid is not null");
            return (Criteria) this;
        }

        public Criteria andOdidEqualTo(Long value) {
            addCriterion("odid =", value, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidNotEqualTo(Long value) {
            addCriterion("odid <>", value, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidGreaterThan(Long value) {
            addCriterion("odid >", value, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidGreaterThanOrEqualTo(Long value) {
            addCriterion("odid >=", value, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidLessThan(Long value) {
            addCriterion("odid <", value, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidLessThanOrEqualTo(Long value) {
            addCriterion("odid <=", value, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidIn(List<Long> values) {
            addCriterion("odid in", values, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidNotIn(List<Long> values) {
            addCriterion("odid not in", values, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidBetween(Long value1, Long value2) {
            addCriterion("odid between", value1, value2, "odid");
            return (Criteria) this;
        }

        public Criteria andOdidNotBetween(Long value1, Long value2) {
            addCriterion("odid not between", value1, value2, "odid");
            return (Criteria) this;
        }

        public Criteria andOidIsNull() {
            addCriterion("oid is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("oid is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(Long value) {
            addCriterion("oid =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(Long value) {
            addCriterion("oid <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(Long value) {
            addCriterion("oid >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(Long value) {
            addCriterion("oid >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(Long value) {
            addCriterion("oid <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(Long value) {
            addCriterion("oid <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<Long> values) {
            addCriterion("oid in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<Long> values) {
            addCriterion("oid not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(Long value1, Long value2) {
            addCriterion("oid between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(Long value1, Long value2) {
            addCriterion("oid not between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andItemidIsNull() {
            addCriterion("itemid is null");
            return (Criteria) this;
        }

        public Criteria andItemidIsNotNull() {
            addCriterion("itemid is not null");
            return (Criteria) this;
        }

        public Criteria andItemidEqualTo(Long value) {
            addCriterion("itemid =", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotEqualTo(Long value) {
            addCriterion("itemid <>", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThan(Long value) {
            addCriterion("itemid >", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThanOrEqualTo(Long value) {
            addCriterion("itemid >=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThan(Long value) {
            addCriterion("itemid <", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThanOrEqualTo(Long value) {
            addCriterion("itemid <=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidIn(List<Long> values) {
            addCriterion("itemid in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotIn(List<Long> values) {
            addCriterion("itemid not in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidBetween(Long value1, Long value2) {
            addCriterion("itemid between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotBetween(Long value1, Long value2) {
            addCriterion("itemid not between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemnumIsNull() {
            addCriterion("itemnum is null");
            return (Criteria) this;
        }

        public Criteria andItemnumIsNotNull() {
            addCriterion("itemnum is not null");
            return (Criteria) this;
        }

        public Criteria andItemnumEqualTo(Long value) {
            addCriterion("itemnum =", value, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumNotEqualTo(Long value) {
            addCriterion("itemnum <>", value, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumGreaterThan(Long value) {
            addCriterion("itemnum >", value, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumGreaterThanOrEqualTo(Long value) {
            addCriterion("itemnum >=", value, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumLessThan(Long value) {
            addCriterion("itemnum <", value, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumLessThanOrEqualTo(Long value) {
            addCriterion("itemnum <=", value, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumIn(List<Long> values) {
            addCriterion("itemnum in", values, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumNotIn(List<Long> values) {
            addCriterion("itemnum not in", values, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumBetween(Long value1, Long value2) {
            addCriterion("itemnum between", value1, value2, "itemnum");
            return (Criteria) this;
        }

        public Criteria andItemnumNotBetween(Long value1, Long value2) {
            addCriterion("itemnum not between", value1, value2, "itemnum");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Long value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Long value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Long> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Long value1, Long value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andTotalfeeIsNull() {
            addCriterion("totalfee is null");
            return (Criteria) this;
        }

        public Criteria andTotalfeeIsNotNull() {
            addCriterion("totalfee is not null");
            return (Criteria) this;
        }

        public Criteria andTotalfeeEqualTo(Long value) {
            addCriterion("totalfee =", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeNotEqualTo(Long value) {
            addCriterion("totalfee <>", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeGreaterThan(Long value) {
            addCriterion("totalfee >", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeGreaterThanOrEqualTo(Long value) {
            addCriterion("totalfee >=", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeLessThan(Long value) {
            addCriterion("totalfee <", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeLessThanOrEqualTo(Long value) {
            addCriterion("totalfee <=", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeIn(List<Long> values) {
            addCriterion("totalfee in", values, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeNotIn(List<Long> values) {
            addCriterion("totalfee not in", values, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeBetween(Long value1, Long value2) {
            addCriterion("totalfee between", value1, value2, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeNotBetween(Long value1, Long value2) {
            addCriterion("totalfee not between", value1, value2, "totalfee");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andItemtitleIsNull() {
            addCriterion("itemtitle is null");
            return (Criteria) this;
        }

        public Criteria andItemtitleIsNotNull() {
            addCriterion("itemtitle is not null");
            return (Criteria) this;
        }

        public Criteria andItemtitleEqualTo(String value) {
            addCriterion("itemtitle =", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotEqualTo(String value) {
            addCriterion("itemtitle <>", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleGreaterThan(String value) {
            addCriterion("itemtitle >", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleGreaterThanOrEqualTo(String value) {
            addCriterion("itemtitle >=", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLessThan(String value) {
            addCriterion("itemtitle <", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLessThanOrEqualTo(String value) {
            addCriterion("itemtitle <=", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLike(String value) {
            addCriterion("itemtitle like", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotLike(String value) {
            addCriterion("itemtitle not like", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleIn(List<String> values) {
            addCriterion("itemtitle in", values, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotIn(List<String> values) {
            addCriterion("itemtitle not in", values, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleBetween(String value1, String value2) {
            addCriterion("itemtitle between", value1, value2, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotBetween(String value1, String value2) {
            addCriterion("itemtitle not between", value1, value2, "itemtitle");
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
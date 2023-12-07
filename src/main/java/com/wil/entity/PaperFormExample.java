package com.wil.entity;

import java.util.ArrayList;
import java.util.List;

public class PaperFormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaperFormExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumIsNull() {
            addCriterion("q_choice_num is null");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumIsNotNull() {
            addCriterion("q_choice_num is not null");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumEqualTo(String value) {
            addCriterion("q_choice_num =", value, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumNotEqualTo(String value) {
            addCriterion("q_choice_num <>", value, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumGreaterThan(String value) {
            addCriterion("q_choice_num >", value, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumGreaterThanOrEqualTo(String value) {
            addCriterion("q_choice_num >=", value, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumLessThan(String value) {
            addCriterion("q_choice_num <", value, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumLessThanOrEqualTo(String value) {
            addCriterion("q_choice_num <=", value, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumLike(String value) {
            addCriterion("q_choice_num like", value, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumNotLike(String value) {
            addCriterion("q_choice_num not like", value, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumIn(List<String> values) {
            addCriterion("q_choice_num in", values, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumNotIn(List<String> values) {
            addCriterion("q_choice_num not in", values, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumBetween(String value1, String value2) {
            addCriterion("q_choice_num between", value1, value2, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceNumNotBetween(String value1, String value2) {
            addCriterion("q_choice_num not between", value1, value2, "qChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreIsNull() {
            addCriterion("q_choice_score is null");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreIsNotNull() {
            addCriterion("q_choice_score is not null");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreEqualTo(String value) {
            addCriterion("q_choice_score =", value, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreNotEqualTo(String value) {
            addCriterion("q_choice_score <>", value, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreGreaterThan(String value) {
            addCriterion("q_choice_score >", value, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreGreaterThanOrEqualTo(String value) {
            addCriterion("q_choice_score >=", value, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreLessThan(String value) {
            addCriterion("q_choice_score <", value, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreLessThanOrEqualTo(String value) {
            addCriterion("q_choice_score <=", value, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreLike(String value) {
            addCriterion("q_choice_score like", value, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreNotLike(String value) {
            addCriterion("q_choice_score not like", value, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreIn(List<String> values) {
            addCriterion("q_choice_score in", values, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreNotIn(List<String> values) {
            addCriterion("q_choice_score not in", values, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreBetween(String value1, String value2) {
            addCriterion("q_choice_score between", value1, value2, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQChoiceScoreNotBetween(String value1, String value2) {
            addCriterion("q_choice_score not between", value1, value2, "qChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumIsNull() {
            addCriterion("q_mul_choice_num is null");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumIsNotNull() {
            addCriterion("q_mul_choice_num is not null");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumEqualTo(String value) {
            addCriterion("q_mul_choice_num =", value, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumNotEqualTo(String value) {
            addCriterion("q_mul_choice_num <>", value, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumGreaterThan(String value) {
            addCriterion("q_mul_choice_num >", value, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumGreaterThanOrEqualTo(String value) {
            addCriterion("q_mul_choice_num >=", value, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumLessThan(String value) {
            addCriterion("q_mul_choice_num <", value, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumLessThanOrEqualTo(String value) {
            addCriterion("q_mul_choice_num <=", value, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumLike(String value) {
            addCriterion("q_mul_choice_num like", value, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumNotLike(String value) {
            addCriterion("q_mul_choice_num not like", value, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumIn(List<String> values) {
            addCriterion("q_mul_choice_num in", values, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumNotIn(List<String> values) {
            addCriterion("q_mul_choice_num not in", values, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumBetween(String value1, String value2) {
            addCriterion("q_mul_choice_num between", value1, value2, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceNumNotBetween(String value1, String value2) {
            addCriterion("q_mul_choice_num not between", value1, value2, "qMulChoiceNum");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreIsNull() {
            addCriterion("q_mul_choice_score is null");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreIsNotNull() {
            addCriterion("q_mul_choice_score is not null");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreEqualTo(String value) {
            addCriterion("q_mul_choice_score =", value, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreNotEqualTo(String value) {
            addCriterion("q_mul_choice_score <>", value, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreGreaterThan(String value) {
            addCriterion("q_mul_choice_score >", value, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreGreaterThanOrEqualTo(String value) {
            addCriterion("q_mul_choice_score >=", value, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreLessThan(String value) {
            addCriterion("q_mul_choice_score <", value, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreLessThanOrEqualTo(String value) {
            addCriterion("q_mul_choice_score <=", value, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreLike(String value) {
            addCriterion("q_mul_choice_score like", value, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreNotLike(String value) {
            addCriterion("q_mul_choice_score not like", value, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreIn(List<String> values) {
            addCriterion("q_mul_choice_score in", values, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreNotIn(List<String> values) {
            addCriterion("q_mul_choice_score not in", values, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreBetween(String value1, String value2) {
            addCriterion("q_mul_choice_score between", value1, value2, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQMulChoiceScoreNotBetween(String value1, String value2) {
            addCriterion("q_mul_choice_score not between", value1, value2, "qMulChoiceScore");
            return (Criteria) this;
        }

        public Criteria andQTofNumIsNull() {
            addCriterion("q_tof_num is null");
            return (Criteria) this;
        }

        public Criteria andQTofNumIsNotNull() {
            addCriterion("q_tof_num is not null");
            return (Criteria) this;
        }

        public Criteria andQTofNumEqualTo(String value) {
            addCriterion("q_tof_num =", value, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumNotEqualTo(String value) {
            addCriterion("q_tof_num <>", value, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumGreaterThan(String value) {
            addCriterion("q_tof_num >", value, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumGreaterThanOrEqualTo(String value) {
            addCriterion("q_tof_num >=", value, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumLessThan(String value) {
            addCriterion("q_tof_num <", value, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumLessThanOrEqualTo(String value) {
            addCriterion("q_tof_num <=", value, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumLike(String value) {
            addCriterion("q_tof_num like", value, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumNotLike(String value) {
            addCriterion("q_tof_num not like", value, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumIn(List<String> values) {
            addCriterion("q_tof_num in", values, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumNotIn(List<String> values) {
            addCriterion("q_tof_num not in", values, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumBetween(String value1, String value2) {
            addCriterion("q_tof_num between", value1, value2, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofNumNotBetween(String value1, String value2) {
            addCriterion("q_tof_num not between", value1, value2, "qTofNum");
            return (Criteria) this;
        }

        public Criteria andQTofScoreIsNull() {
            addCriterion("q_tof_score is null");
            return (Criteria) this;
        }

        public Criteria andQTofScoreIsNotNull() {
            addCriterion("q_tof_score is not null");
            return (Criteria) this;
        }

        public Criteria andQTofScoreEqualTo(String value) {
            addCriterion("q_tof_score =", value, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreNotEqualTo(String value) {
            addCriterion("q_tof_score <>", value, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreGreaterThan(String value) {
            addCriterion("q_tof_score >", value, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreGreaterThanOrEqualTo(String value) {
            addCriterion("q_tof_score >=", value, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreLessThan(String value) {
            addCriterion("q_tof_score <", value, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreLessThanOrEqualTo(String value) {
            addCriterion("q_tof_score <=", value, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreLike(String value) {
            addCriterion("q_tof_score like", value, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreNotLike(String value) {
            addCriterion("q_tof_score not like", value, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreIn(List<String> values) {
            addCriterion("q_tof_score in", values, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreNotIn(List<String> values) {
            addCriterion("q_tof_score not in", values, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreBetween(String value1, String value2) {
            addCriterion("q_tof_score between", value1, value2, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQTofScoreNotBetween(String value1, String value2) {
            addCriterion("q_tof_score not between", value1, value2, "qTofScore");
            return (Criteria) this;
        }

        public Criteria andQFillNumIsNull() {
            addCriterion("q_fill_num is null");
            return (Criteria) this;
        }

        public Criteria andQFillNumIsNotNull() {
            addCriterion("q_fill_num is not null");
            return (Criteria) this;
        }

        public Criteria andQFillNumEqualTo(String value) {
            addCriterion("q_fill_num =", value, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumNotEqualTo(String value) {
            addCriterion("q_fill_num <>", value, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumGreaterThan(String value) {
            addCriterion("q_fill_num >", value, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumGreaterThanOrEqualTo(String value) {
            addCriterion("q_fill_num >=", value, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumLessThan(String value) {
            addCriterion("q_fill_num <", value, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumLessThanOrEqualTo(String value) {
            addCriterion("q_fill_num <=", value, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumLike(String value) {
            addCriterion("q_fill_num like", value, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumNotLike(String value) {
            addCriterion("q_fill_num not like", value, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumIn(List<String> values) {
            addCriterion("q_fill_num in", values, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumNotIn(List<String> values) {
            addCriterion("q_fill_num not in", values, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumBetween(String value1, String value2) {
            addCriterion("q_fill_num between", value1, value2, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillNumNotBetween(String value1, String value2) {
            addCriterion("q_fill_num not between", value1, value2, "qFillNum");
            return (Criteria) this;
        }

        public Criteria andQFillScoreIsNull() {
            addCriterion("q_fill_score is null");
            return (Criteria) this;
        }

        public Criteria andQFillScoreIsNotNull() {
            addCriterion("q_fill_score is not null");
            return (Criteria) this;
        }

        public Criteria andQFillScoreEqualTo(String value) {
            addCriterion("q_fill_score =", value, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreNotEqualTo(String value) {
            addCriterion("q_fill_score <>", value, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreGreaterThan(String value) {
            addCriterion("q_fill_score >", value, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreGreaterThanOrEqualTo(String value) {
            addCriterion("q_fill_score >=", value, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreLessThan(String value) {
            addCriterion("q_fill_score <", value, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreLessThanOrEqualTo(String value) {
            addCriterion("q_fill_score <=", value, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreLike(String value) {
            addCriterion("q_fill_score like", value, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreNotLike(String value) {
            addCriterion("q_fill_score not like", value, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreIn(List<String> values) {
            addCriterion("q_fill_score in", values, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreNotIn(List<String> values) {
            addCriterion("q_fill_score not in", values, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreBetween(String value1, String value2) {
            addCriterion("q_fill_score between", value1, value2, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQFillScoreNotBetween(String value1, String value2) {
            addCriterion("q_fill_score not between", value1, value2, "qFillScore");
            return (Criteria) this;
        }

        public Criteria andQSaqNumIsNull() {
            addCriterion("q_SAQ_num is null");
            return (Criteria) this;
        }

        public Criteria andQSaqNumIsNotNull() {
            addCriterion("q_SAQ_num is not null");
            return (Criteria) this;
        }

        public Criteria andQSaqNumEqualTo(String value) {
            addCriterion("q_SAQ_num =", value, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumNotEqualTo(String value) {
            addCriterion("q_SAQ_num <>", value, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumGreaterThan(String value) {
            addCriterion("q_SAQ_num >", value, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumGreaterThanOrEqualTo(String value) {
            addCriterion("q_SAQ_num >=", value, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumLessThan(String value) {
            addCriterion("q_SAQ_num <", value, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumLessThanOrEqualTo(String value) {
            addCriterion("q_SAQ_num <=", value, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumLike(String value) {
            addCriterion("q_SAQ_num like", value, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumNotLike(String value) {
            addCriterion("q_SAQ_num not like", value, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumIn(List<String> values) {
            addCriterion("q_SAQ_num in", values, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumNotIn(List<String> values) {
            addCriterion("q_SAQ_num not in", values, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumBetween(String value1, String value2) {
            addCriterion("q_SAQ_num between", value1, value2, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqNumNotBetween(String value1, String value2) {
            addCriterion("q_SAQ_num not between", value1, value2, "qSaqNum");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreIsNull() {
            addCriterion("q_SAQ_score is null");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreIsNotNull() {
            addCriterion("q_SAQ_score is not null");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreEqualTo(String value) {
            addCriterion("q_SAQ_score =", value, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreNotEqualTo(String value) {
            addCriterion("q_SAQ_score <>", value, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreGreaterThan(String value) {
            addCriterion("q_SAQ_score >", value, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreGreaterThanOrEqualTo(String value) {
            addCriterion("q_SAQ_score >=", value, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreLessThan(String value) {
            addCriterion("q_SAQ_score <", value, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreLessThanOrEqualTo(String value) {
            addCriterion("q_SAQ_score <=", value, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreLike(String value) {
            addCriterion("q_SAQ_score like", value, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreNotLike(String value) {
            addCriterion("q_SAQ_score not like", value, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreIn(List<String> values) {
            addCriterion("q_SAQ_score in", values, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreNotIn(List<String> values) {
            addCriterion("q_SAQ_score not in", values, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreBetween(String value1, String value2) {
            addCriterion("q_SAQ_score between", value1, value2, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQSaqScoreNotBetween(String value1, String value2) {
            addCriterion("q_SAQ_score not between", value1, value2, "qSaqScore");
            return (Criteria) this;
        }

        public Criteria andQProgramNumIsNull() {
            addCriterion("q_program_num is null");
            return (Criteria) this;
        }

        public Criteria andQProgramNumIsNotNull() {
            addCriterion("q_program_num is not null");
            return (Criteria) this;
        }

        public Criteria andQProgramNumEqualTo(String value) {
            addCriterion("q_program_num =", value, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumNotEqualTo(String value) {
            addCriterion("q_program_num <>", value, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumGreaterThan(String value) {
            addCriterion("q_program_num >", value, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumGreaterThanOrEqualTo(String value) {
            addCriterion("q_program_num >=", value, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumLessThan(String value) {
            addCriterion("q_program_num <", value, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumLessThanOrEqualTo(String value) {
            addCriterion("q_program_num <=", value, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumLike(String value) {
            addCriterion("q_program_num like", value, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumNotLike(String value) {
            addCriterion("q_program_num not like", value, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumIn(List<String> values) {
            addCriterion("q_program_num in", values, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumNotIn(List<String> values) {
            addCriterion("q_program_num not in", values, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumBetween(String value1, String value2) {
            addCriterion("q_program_num between", value1, value2, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramNumNotBetween(String value1, String value2) {
            addCriterion("q_program_num not between", value1, value2, "qProgramNum");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreIsNull() {
            addCriterion("q_program_score is null");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreIsNotNull() {
            addCriterion("q_program_score is not null");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreEqualTo(String value) {
            addCriterion("q_program_score =", value, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreNotEqualTo(String value) {
            addCriterion("q_program_score <>", value, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreGreaterThan(String value) {
            addCriterion("q_program_score >", value, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreGreaterThanOrEqualTo(String value) {
            addCriterion("q_program_score >=", value, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreLessThan(String value) {
            addCriterion("q_program_score <", value, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreLessThanOrEqualTo(String value) {
            addCriterion("q_program_score <=", value, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreLike(String value) {
            addCriterion("q_program_score like", value, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreNotLike(String value) {
            addCriterion("q_program_score not like", value, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreIn(List<String> values) {
            addCriterion("q_program_score in", values, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreNotIn(List<String> values) {
            addCriterion("q_program_score not in", values, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreBetween(String value1, String value2) {
            addCriterion("q_program_score between", value1, value2, "qProgramScore");
            return (Criteria) this;
        }

        public Criteria andQProgramScoreNotBetween(String value1, String value2) {
            addCriterion("q_program_score not between", value1, value2, "qProgramScore");
            return (Criteria) this;
        }
    }

    /**
     */
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
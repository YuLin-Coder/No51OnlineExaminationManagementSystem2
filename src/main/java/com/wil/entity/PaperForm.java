package com.wil.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class PaperForm implements Serializable {
    private Integer id;

    /**
     * 单选题数目
     */
    private String qChoiceNum;

    /**
     * 单选题分数
     */
    private String qChoiceScore;

    /**
     * 多选题数目
     */
    private String qMulChoiceNum;

    /**
     * 多选题分数
     */
    private String qMulChoiceScore;

    /**
     * 判断题数目
     */
    private String qTofNum;

    /**
     * 判断题分数
     */
    private String qTofScore;

    /**
     * 填空题数目
     */
    private String qFillNum;

    /**
     * 填空题分数
     */
    private String qFillScore;

    /**
     * 简答题数目
     */
    private String qSaqNum;

    /**
     * 简答题分数
     */
    private String qSaqScore;

    /**
     * 编程题数目
     */
    private String qProgramNum;

    /**
     * 编程题分数
     */
    private String qProgramScore;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getqChoiceNum() {
        return qChoiceNum;
    }

    public void setqChoiceNum(String qChoiceNum) {
        this.qChoiceNum = qChoiceNum;
    }

    public String getqChoiceScore() {
        return qChoiceScore;
    }

    public void setqChoiceScore(String qChoiceScore) {
        this.qChoiceScore = qChoiceScore;
    }

    public String getqMulChoiceNum() {
        return qMulChoiceNum;
    }

    public void setqMulChoiceNum(String qMulChoiceNum) {
        this.qMulChoiceNum = qMulChoiceNum;
    }

    public String getqMulChoiceScore() {
        return qMulChoiceScore;
    }

    public void setqMulChoiceScore(String qMulChoiceScore) {
        this.qMulChoiceScore = qMulChoiceScore;
    }

    public String getqTofNum() {
        return qTofNum;
    }

    public void setqTofNum(String qTofNum) {
        this.qTofNum = qTofNum;
    }

    public String getqTofScore() {
        return qTofScore;
    }

    public void setqTofScore(String qTofScore) {
        this.qTofScore = qTofScore;
    }

    public String getqFillNum() {
        return qFillNum;
    }

    public void setqFillNum(String qFillNum) {
        this.qFillNum = qFillNum;
    }

    public String getqFillScore() {
        return qFillScore;
    }

    public void setqFillScore(String qFillScore) {
        this.qFillScore = qFillScore;
    }

    public String getqSaqNum() {
        return qSaqNum;
    }

    public void setqSaqNum(String qSaqNum) {
        this.qSaqNum = qSaqNum;
    }

    public String getqSaqScore() {
        return qSaqScore;
    }

    public void setqSaqScore(String qSaqScore) {
        this.qSaqScore = qSaqScore;
    }

    public String getqProgramNum() {
        return qProgramNum;
    }

    public void setqProgramNum(String qProgramNum) {
        this.qProgramNum = qProgramNum;
    }

    public String getqProgramScore() {
        return qProgramScore;
    }

    public void setqProgramScore(String qProgramScore) {
        this.qProgramScore = qProgramScore;
    }
}
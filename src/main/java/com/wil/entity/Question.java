package com.wil.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Question implements Serializable {
    private Integer id;

    /**
     * 题目名称
     */
    private String questionName;

    /**
     * 选项a
     */
    private String optionA;

    /**
     * 选项b
     */
    private String optionB;

    /**
     * 选项c
     */
    private String optionC;

    /**
     * 选项d
     */
    private String optionD;

    /**
     * 题目类型id
     */
    private Integer typeId;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 题目难度：容易，中等，较难
     */
    private String difficulty;

    /**
     * 题目解析
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
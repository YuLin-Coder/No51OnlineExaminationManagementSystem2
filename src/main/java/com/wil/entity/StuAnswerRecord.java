package com.wil.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class StuAnswerRecord implements Serializable {
    private Integer id;

    /**
     * 试卷id
     */
    private Integer paperId;

    private Integer stuId;

    /**
     * 题目id
     */
    private Integer questionId;

    /**
     * 题目答案
     */
    private String answer;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public StuAnswerRecord() {}

    public StuAnswerRecord(Integer paperId, Integer stuId, Integer questionId, String answer) {
        this.paperId = paperId;
        this.stuId = stuId;
        this.questionId = questionId;
        this.answer = answer;
    }
}
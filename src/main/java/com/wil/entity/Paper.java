package com.wil.entity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;

/**
 * @author 
 */
public class Paper implements Serializable {
    private Integer id;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 问题id组合
     */
    private String questionId;

    /**
     * 试卷开始时间
     */
    private String beginTime;

    /**
     * 试卷结束时间
     */
    private String endTime;

    /**
     * 考试时长
     */
    private String allowTime;

    /**
     * 试卷总分
     */
    private String score;

    /**
     * 考试状态：未开始，已结束
     */
    private String paperState;

    /**
     * 试卷类型：正式，模拟
     */
    private String paperType;

    /**
     * 专业班级id
     */
    private Integer majorId;

    /**
     * 试卷组成id
     */
    private Integer paperFormId;

    /**
     * 出卷老师id
     */
    private Integer teacherId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAllowTime() {
        return allowTime;
    }

    public void setAllowTime(String allowTime) {
        this.allowTime = allowTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPaperState() {
        return paperState;
    }

    public void setPaperState(String paperState) {
        this.paperState = paperState;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getPaperFormId() {
        return paperFormId;
    }

    public void setPaperFormId(Integer paperFormId) {
        this.paperFormId = paperFormId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 判断开始时间是否在当前时间之后
     * @return true 是的 试卷已经开始
     * false 否 试卷未开始
     */
    public boolean isStart() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        DateTime beginTime = formatter.parseDateTime(getBeginTime());
        return beginTime.isBeforeNow();
    }

    /**
     * 判断结束时间是否在当前时间之后
     * @return true 是的 试卷已经结束
     * false 否 试卷未结束
     */
    public boolean isEnd() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        DateTime endTime = formatter.parseDateTime(getEndTime());
        return endTime.isBeforeNow();
    }

}
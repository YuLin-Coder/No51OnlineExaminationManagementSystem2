package com.wil.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Score implements Serializable {
    private Integer id;

    /**
     * 考生id
     */
    private Integer stuId;

    /**
     * 试卷id
     */
    private Integer paperId;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 试卷分数
     */
    private String score;

    /**
     * 错题id集合
     */
    private String wrongIds;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWrongIds() {
        return wrongIds;
    }

    public void setWrongIds(String wrongIds) {
        this.wrongIds = wrongIds;
    }

    public Score() {}

    public Score(Integer stuId, Integer paperId, String paperName, String score, String wrongIds) {
        this.stuId = stuId;
        this.paperId = paperId;
        this.paperName = paperName;
        this.score = score;
        this.wrongIds = wrongIds;
    }
}
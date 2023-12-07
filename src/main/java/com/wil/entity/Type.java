package com.wil.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Type implements Serializable {
    private Integer id;

    /**
     * 题目类型
     */
    private String typeName;

    /**
     * 各个类型题目的分数
     */
    private String score;

    /**
     * 该类型题目说明
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
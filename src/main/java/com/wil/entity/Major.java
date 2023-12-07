package com.wil.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Major implements Serializable {
    private Integer id;

    /**
     * 专业班级
     */
    private String major;

    /**
     * 学院
     */
    private String academy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }
}
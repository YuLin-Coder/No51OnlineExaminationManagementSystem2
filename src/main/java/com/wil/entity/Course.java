package com.wil.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Course implements Serializable {
    private Integer id;

    private String courseName;

    /**
     * 任课老师姓名
     */
    private String teacherName;

    /**
     * 该门课的出题老师(默认一门课一个老师出题)
     */
    private Integer teacherId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
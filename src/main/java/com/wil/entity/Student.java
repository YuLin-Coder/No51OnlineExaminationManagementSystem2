package com.wil.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Student implements Serializable {
    private Integer id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生登录密码
     */
    private String password;

    /**
     * 学号
     */
    private String stuNumber;

    /**
     * 专业班级
     */
    private String major;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 性别
     */
    private String sex;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
package com.wil.service;

import com.wil.entity.Student;

import java.util.Map;

/**
 * Created by wil on 2018/5/7.
 */
public interface StudentService {

    /**
     * 学生登录
     * @param stuNumber
     * @param stuPassword
     * @return
     */
    Student login(String stuNumber, String stuPassword);

    Student findById(Integer id);

    /**
     * 学生修改密码
     * @param id
     * @param password
     */
    void changePassword(Integer id, String password);

    Student findByStuNumber(String stuNumber);
}

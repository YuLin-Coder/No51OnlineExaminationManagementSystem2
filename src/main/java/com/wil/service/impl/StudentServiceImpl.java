package com.wil.service.impl;

import com.wil.entity.Student;
import com.wil.exception.ServiceException;
import com.wil.mapper.StudentMapper;
import com.wil.service.StudentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by wil on 2018/5/7.
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper studentMapper;
    @Value("${jdbc.salt}")
    private String salt;

    @Override
    public Student login(String stuNumber, String password) throws ServiceException{
        Student student = studentMapper.findByStuNumber(stuNumber);
        if(student == null) {
            throw new ServiceException("该学号不存在，请重新输入");
        }
        String codedPass = DigestUtils.md5Hex(salt + password);
        if(!codedPass.equals(student.getPassword())) {
            throw new ServiceException("密码错误");
        }
        return student;
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    /**
     * 学生修改密码
     * @param id
     * @param password
     */
    @Override
    public void changePassword(Integer id, String password) {
        Student student = studentMapper.selectByPrimaryKey(id);
        if(student!= null && StringUtils.isNotEmpty(password)) {
            student.setPassword(DigestUtils.md5Hex(salt + password));
        }
        studentMapper.updateByPrimaryKeySelective(student);

    }

    @Override
    public Student findByStuNumber(String stuNumber) {
        return studentMapper.findByStuNumber(stuNumber);
    }
}

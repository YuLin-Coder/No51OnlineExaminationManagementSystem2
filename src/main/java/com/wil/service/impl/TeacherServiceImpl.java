package com.wil.service.impl;

import com.wil.entity.*;
import com.wil.exception.ServiceException;
import com.wil.mapper.AnnounceMapper;
import com.wil.mapper.CourseMapper;
import com.wil.mapper.TeacherMapper;
import com.wil.service.TeacherService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wil on 2018/5/8.
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private AnnounceMapper announceMapper;
    @Value("${jdbc.salt}")
    private String salt;

    private final static Integer ROLE_ADMIN_ID = 1;

    /**
     * 教师登录
     * @param teaNumber 职工号
     * @param password 密码
     * @return
     */
    @Override
    public Teacher login(String teaNumber, String password) {
        Teacher teacher = teacherMapper.findByWorkNumber(teaNumber);
        if(teacher == null) {
            throw new ServiceException("该职工号不存在，请重新输入");
        }
        String codedPass = DigestUtils.md5Hex(salt + password);
        if(!codedPass.equals(teacher.getPassword())) {
            throw new ServiceException("密码错误");
        }
        return teacher;
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据教师id查找所教课程
     * @param teacherId
     * @return
     */
    @Override
    public List<Course> findAllCourseByTeacherId(Integer teacherId) {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andTeacherIdEqualTo(teacherId);
        return courseMapper.selectByExample(courseExample);
    }

    /**
     * 根据课程id删除该课程
     * @param id
     */
    @Override
    public void delCourseById(Integer id) throws ServiceException {
        Course course = courseMapper.selectByPrimaryKey(id);
        if(course == null) {
            throw new ServiceException("该课程不存在！");
        }
        courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveCourse(String courseName, Integer teacherId) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);

        Course course = new Course();
        course.setCourseName(courseName);
        course.setTeacherId(teacherId);
        course.setTeacherName(teacher.getName());
        courseMapper.insertSelective(course);
    }

    /**
     * 查找所有的系统公告
     * @return
     */
    @Override
    public List<Announce> findAllSystemAnnounce() {
        AnnounceExample announceExample = new AnnounceExample();
        announceExample.createCriteria().andRoleIdEqualTo(ROLE_ADMIN_ID);
        return announceMapper.selectByExample(announceExample);
    }

    @Override
    public Course findCourseById(String courseId) {
        return courseMapper.selectByPrimaryKey(Integer.parseInt(courseId));
    }


}

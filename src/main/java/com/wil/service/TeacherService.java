package com.wil.service;

import com.wil.entity.Announce;
import com.wil.entity.Course;
import com.wil.entity.Question;
import com.wil.entity.Teacher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wil on 2018/5/8.
 */
public interface TeacherService {

    /**
     * 教师登录
     * @param teacherID
     * @param teacherPassword
     * @return
     */
    Teacher login(String teacherID, String teacherPassword);

    Teacher findById(Integer id);

    /**
     * 根据教师id查找所教课程
     * @param teacherId
     * @return
     */
    List<Course> findAllCourseByTeacherId(Integer teacherId);

    /**
     * 根据课程id删除该课程
     * @param id
     */
    void delCourseById(Integer id);

    /**
     * 添加课程
     * @param courseName
     * @param teacherId
     */
    void saveCourse(String courseName, Integer teacherId);

    /**
     * 查找所有的系统公告
     * @return
     */
    List<Announce> findAllSystemAnnounce();

    Course findCourseById(String courseId);
}

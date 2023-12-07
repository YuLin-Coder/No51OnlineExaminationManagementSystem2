package com.wil.controller;

import com.wil.entity.Course;
import com.wil.service.ScoreService;
import com.wil.service.TeacherService;
import com.wil.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by wil on 2018/5/17.
 */
@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/course")
    public String chart(Model model, HttpSession session) {
        List<Course> courseList = teacherService.findAllCourseByTeacherId((Integer) session.getAttribute("teacherId"));
        model.addAttribute("courseList", courseList);
        return "teacher/chart";
    }

    @GetMapping("/courseChart")
    public String courseChart(String courseId, Model model) {

        Course course = teacherService.findCourseById(courseId);
        model.addAttribute("course", course);
        return "teacher/scoreCourse";
    }

    /**
     * 根据课程id统计所有试卷考试学生成绩
     * @param id courseId
     * @return
     */
    @PostMapping("/courseChart/{id:\\d+}")
    @ResponseBody
    public AjaxResult courseChart(@PathVariable Integer id) {
        List<Map<String, Object>> scoreCount = scoreService.countByCourse(String.valueOf(id));
        return AjaxResult.successWithData(scoreCount);
    }

    @GetMapping("/major")
    public String major(Model model, HttpSession session) {
        List<Course> courseList = teacherService.findAllCourseByTeacherId((Integer) session.getAttribute("teacherId"));
        model.addAttribute("courseList", courseList);
        return "teacher/majorChart";
    }

    @GetMapping("/majorChart")
    public String major(String majorName, String courseId, Model model) {
        Course course = teacherService.findCourseById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("majorName", majorName);

        return "teacher/scoreMajor";
    }

    /**
     * 根据课程和专业统计学生分数
     * @param id
     * @param majorName
     * @return
     */
    @GetMapping("/majorChart/{id:\\d+}")
    @ResponseBody
    public AjaxResult majorChart(@PathVariable Integer id, String majorName) {
        List<Map<String, Object>> scoreCount = scoreService.countByMajorAndCourse(majorName, id);
        return AjaxResult.successWithData(scoreCount);
    }


    @GetMapping("/student/chart")
    public String studentChart() {
        return "student/scoreChart";
    }

    /**
     *雷达图 成绩与平均成绩比较
     * @return
     */
    @PostMapping("/student/chart/{id:\\d+}")
    @ResponseBody
    public AjaxResult stuChart(@PathVariable Integer id) {

        List<Map<String, Object>> aveAndMyScoreList = scoreService.averageScore(id);

        return AjaxResult.successWithData(aveAndMyScoreList);
    }

    /**
     * 统计该学生成绩分布
     * @param session
     * @return
     */
    @GetMapping("/student/chart2")
    @ResponseBody
    public AjaxResult stuSelfChart(HttpSession session) {
        List<Map<String, Object>> everyScoreList = scoreService.countByLevel(session.getAttribute("studentId"));
        return AjaxResult.successWithData(everyScoreList);
    }


}

package com.wil.controller;

import com.wil.entity.*;
import com.wil.mapper.StudentMapper;
import com.wil.service.PaperService;
import com.wil.service.QuestionService;
import com.wil.service.StudentService;
import com.wil.service.TeacherService;
import com.wil.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by wil on 2018/5/8.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public String login(String teacherID, String teacherPassword,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        try {
            Teacher teacher = teacherService.login(teacherID, teacherPassword);
            session.setAttribute("teacherId", teacher.getId());
            session.setAttribute("teacher", teacher);
            return "redirect:/teacher/home/"+teacher.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message_tea", e.getMessage());
            return "redirect:/";
        }

    }

    @GetMapping("/home/{id:\\d+}")
    public String home(Model model, @PathVariable Integer id) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("teacherId");
        session.removeAttribute("teacher");
        return "redirect:/";
    }

    /**
     * 展示该教师所教课程
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/course/list")
    public String courseList(Model model, HttpSession session) {
        Integer teacherId = (Integer) session.getAttribute("teacherId");
        List<Course> courseList = teacherService.findAllCourseByTeacherId(teacherId);
        model.addAttribute("courseList", courseList);
        return "teacher/courseList";
    }

    /**
     * 添加课程
     * @param courseName
     * @param teacherId
     * @return
     */
    @GetMapping("/course/new")
    @ResponseBody
    public AjaxResult newCourse(String courseName, String teacherId) {
        teacherService.saveCourse(courseName, Integer.parseInt(teacherId));
        return AjaxResult.success();
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @GetMapping("/course/del/{id:\\d+}")
    @ResponseBody
    public AjaxResult delCourse(@PathVariable Integer id) {
        try {
            teacherService.delCourseById(id);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }

    /**
     * 查看系统公告列表
     * @param model
     * @return
     */
    @GetMapping("/announce/system")
    public String announceSystem(Model model) {
        List<Announce> announceList = teacherService.findAllSystemAnnounce();
        model.addAttribute("announceList", announceList);
        return "teacher/sysAnnounceList";
    }

    /**
     * 考试管理 可以修改考试时间
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/exam")
    public String exam(Model model, HttpSession session) {
        Integer id = (Integer) session.getAttribute("teacherId");

        List<Paper> paperList = paperService.findUnDoPaperListByTeacherId(id);
        model.addAttribute("paperList", paperList);

        return "teacher/examList";
    }

    @PostMapping("/editPaper/{id:\\d+}")
    @ResponseBody
    public AjaxResult editExam(@PathVariable Integer id, String beginTime, String endTime, String allowTime) {

        paperService.editPaperById(id, beginTime, endTime, allowTime);
        return AjaxResult.success();
    }

    /**
     * 试卷复查
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/reviewPaper")
    public String reviewPaper(Model model, HttpSession session) {
        List<Paper> paperList = paperService.findDonePaperListByTeacherId(session.getAttribute("teacherId"));
        model.addAttribute("paperList", paperList);
        return "teacher/review";
    }

    @GetMapping ("/reviewRes")
    public String reviewPaper(String stuNumber, String paperId, Model model) {
        List<StuAnswerRecord> answerRecordList = paperService.findAnswerRecordByStuAndPaper(stuNumber, paperId);
        List<Question> questionList = questionService.findByAnswerRecordList(answerRecordList);
        Map<String, String> stuAnswer = questionService.findMapByStuAnswerRecordAndQuestionList(answerRecordList, questionList);

        Student student = studentService.findByStuNumber(stuNumber);
        Paper paper = paperService.findById(Integer.parseInt(paperId));

        model.addAttribute("questionList", questionList);
        model.addAttribute("stuAnswer", stuAnswer);
        model.addAttribute("student", student);
        model.addAttribute("paper", paper);

        return "teacher/answerRecord";
    }

    @GetMapping("/help")
    public String help() {
        return "teacher/help";
    }

    /**
     * 修改试卷状态：未开始——>已结束
     * @param id
     * @return
     */
    @GetMapping("/changePaperState/{id:\\d+}")
    @ResponseBody
    public AjaxResult changPaperState(@PathVariable Integer id) {
        paperService.changeStateById(id);
        return AjaxResult.success();
    }






}

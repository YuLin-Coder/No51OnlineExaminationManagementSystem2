package com.wil.controller;

import com.wil.entity.*;
import com.wil.exception.NotFoundException;
import com.wil.exception.ServiceException;
import com.wil.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * Created by wil on 2018/5/7.
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private DiskService diskService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    private final static Integer qChoiceType = 1;
    private final static Integer qMulChoiceType = 2;
    private final static Integer qTofType = 3;
    private final static Integer qFillType = 4;
    private final static Integer qSaqType = 5;
    private final static Integer qProgramType = 6;

    /**
     * 学生登录 验证学号和密码
     * @param studentID 学号(stuNumber)
     * @param studentPassword 密码
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/")
    public String login(String studentID, String studentPassword,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        try {
            Student student = studentService.login(studentID, studentPassword);
            session.setAttribute("studentId", student.getId());
            session.setAttribute("student", student);
            return "redirect:/student/home/"+student.getId();
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }

    }

    /**
     * 学生登录后来到home页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/student/home/{id:\\d+}")
    public String home(@PathVariable Integer id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/home";
    }

    /**
     * 学生修改密码
     * @param id
     * @return
     */
    @GetMapping("/student/{id:\\d+}/changePass")
    public String changePass(@PathVariable Integer id) {
        return "student/changePass";
    }


    @PostMapping("/student/{id:\\d+}/changePass")
    public String changePass(@PathVariable Integer id, String password, HttpSession session) {
        studentService.changePassword(id, password);
        session.removeAttribute("studentId");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("studentId");
        session.removeAttribute("student");
        return "redirect:/";
    }

    @GetMapping("/student/announce/system")
    public String announceSystem(Model model) {
        List<Announce> announceList = teacherService.findAllSystemAnnounce();
        model.addAttribute("announceList", announceList);
        return "student/sysAnnounceList";
    }

    /**
     * 学生进入我的考试列表
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/student/exam")
    public String exam(Model model, HttpSession session) {
        Integer id = (Integer) session.getAttribute("studentId");
        Student student = studentService.findById(id);
        //正式试卷
        List<Paper> paperList = paperService.findPaperListByMajor(student.getMajor());
        model.addAttribute("paperList", paperList);

        //模拟试卷
        List<Paper> practicePaperList = paperService.findPracticePapersByMajor(student.getMajor());
        model.addAttribute("practicePaperList", practicePaperList);
        return "student/examList";
    }

    /**
     * 学生进入考试
     * @param stuId
     * @param paperId
     * @param model
     * @return
     */
    @GetMapping("/student/{stuId:\\d+}/paper/{paperId:\\d+}")
    public String doPaper(@PathVariable("stuId") Integer stuId,
                          @PathVariable("paperId") Integer paperId,
                          Model model) {

        Paper paper = paperService.findById(paperId);
        Set<Question> qChoiceList = paperService.findQuestionsByPaperIdAndType(paperId, qChoiceType);
        Set<Question> qMulChoiceList = paperService.findQuestionsByPaperIdAndType(paperId, qMulChoiceType);
        Set<Question> qTofList = paperService.findQuestionsByPaperIdAndType(paperId, qTofType);
        Set<Question> qFillList = paperService.findQuestionsByPaperIdAndType(paperId, qFillType);
        Set<Question> qSaqList = paperService.findQuestionsByPaperIdAndType(paperId, qSaqType);
        Set<Question> qProgramList = paperService.findQuestionsByPaperIdAndType(paperId, qProgramType);

        model.addAttribute("paper", paper);
        model.addAttribute("qChoiceList", qChoiceList);
        model.addAttribute("qMulChoiceList",qMulChoiceList);
        model.addAttribute("qTofList", qTofList);
        model.addAttribute("qFillList", qFillList);
        model.addAttribute("qSaqList", qSaqList);
        model.addAttribute("qProgramList", qProgramList);

        return "student/paperDetail";
    }

    /**
     * 学生进行考试 批改试卷
     * @param stuId
     * @param paperId
     * @param request
     * @return
     */
    @PostMapping("/student/{stuId:\\d+}/paper/{paperId:\\d+}")
    public String doPaper(@PathVariable("stuId") Integer stuId,
                          @PathVariable("paperId") Integer paperId,
                          HttpServletRequest request) {
        paperService.markPaper(stuId, paperId, request);

        return "redirect:/student/home/" + stuId;
    }

    /**
     * 查询成绩列表
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/student/score/{id:\\d+}")
    public String scoreList(@PathVariable Integer id, Model model) {

        List<Score> scoreList = scoreService.findByStuId(id);
        model.addAttribute("scoreList", scoreList);
        return "student/scoreList";
    }

    @GetMapping("/student/chart")
    public String chart() {
        return "student/chart";
    }

    @GetMapping("/student/help")
    public String help() {
        return "student/help";
    }

    @GetMapping("/student/disk")
    public String disk(@RequestParam(name = "_", required = false, defaultValue = "0") Integer pId,
                       Model model) {
        if(pId != 0) {
            Disk disk = diskService.findById(pId);
            model.addAttribute("disk", disk);
        }

        List<Disk> diskList = diskService.findDiskListByPId(pId);
        model.addAttribute("diskList", diskList);
        return "student/disk";
    }

    @GetMapping("/search")
    public String searchQuestion(String questionId, Model model,
                                 RedirectAttributes redirectAttributes) {

        if(!StringUtils.isNumeric(questionId)) {
            String message = "温馨提示：搜索框只能输入纯数字！";
            model.addAttribute("message", message);
            return "student/home";
        } else {
            Question question = questionService.findById(Integer.parseInt(questionId));

            if(question != null) {
                Course course = questionService.findByCourseId(question.getCourseId());
                model.addAttribute("question", question);
                model.addAttribute("course", course);
                return "student/question";
            } else {
                String message = "题号不存在，请勿乱输！";
                model.addAttribute("message", message);
                return "student/home";
            }
        }


    }





}

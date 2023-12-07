package com.wil.controller;

import com.github.pagehelper.PageInfo;
import com.wil.entity.Course;
import com.wil.entity.Question;
import com.wil.entity.Type;
import com.wil.exception.ServiceException;
import com.wil.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wil on 2018/5/10.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public String list(@RequestParam(name = "p", required = false, defaultValue = "1") Integer pageNo,
                       Model model) {
        PageInfo<Question> pageInfo = questionService.pageForQuestionList(pageNo);
        model.addAttribute("page", pageInfo);
        return "question/list";
    }

    @GetMapping("/show/{id:\\d+}")
    public String show(@PathVariable Integer id, Model model) {
        Question question = questionService.findById(id);
        Course course = questionService.findByCourseId(question.getCourseId());
        model.addAttribute("question", question);
        model.addAttribute("course", course);
        return "question/show";
    }

    /**
     * 新增试题
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/new")
    public String add(Model model, HttpSession session) {
        List<Type> typeList = questionService.findAllType();
        List<Course> courseList = questionService.findTeacherCourse((Integer) session.getAttribute("teacherId"));
        model.addAttribute("typeList", typeList);
        model.addAttribute("courseList", courseList);
        return "question/new";
    }

    @PostMapping("/new")
    public String add(Question question, RedirectAttributes redirectAttributes) {
        questionService.saveNewQuestion(question);
        redirectAttributes.addFlashAttribute("message", "试题添加成功！");
        return "redirect:/question/show/" + question.getId();
    }

    /**
     * 修改试题信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id:\\d+}")
    public String edit(@PathVariable Integer id, Model model) {
        Question question = questionService.findById(id);
        model.addAttribute("question", question);
        return "question/edit";
    }

    @PostMapping("/edit/{id:\\d+}")
    public String edit(@PathVariable Integer id, String questionName,
                       String answer, String remark,
                       RedirectAttributes redirectAttributes) {
        try {
            questionService.editQuestion(id, questionName, answer, remark);
            redirectAttributes.addFlashAttribute("message", "修改成功");
            return "redirect:/question/show/" + id;
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/question";
        }
    }

    /**
     * 删除试题
     * @param id
     * @return
     */
    @GetMapping("/delete/{id:\\d+}")
    public String delete(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
        return "redirect:/question";
    }




}

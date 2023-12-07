package com.wil.service;

import com.github.pagehelper.PageInfo;
import com.wil.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Created by wil on 2018/5/11.
 */
public interface PaperService {
    /**
     *查找该老师所有试卷并分页
     * @param teacherId
     * @param pageNo
     * @return
     */
    PageInfo<Paper> pageForPaperList(Integer teacherId, Integer pageNo);

    Paper findById(Integer id);

    /**
     * 根据课程id查询课程
     * @param courseId
     * @return
     */
    Course findCourseById(Integer courseId);

    void newPaperForm(PaperForm paperForm);

    /**
     * 查找一个老师所教的所有课程
     * @param teacherId
     * @return
     */
    List<Course> findCourseListByTeacherId(Integer teacherId);

    Major findMajorByMajorName(String major);

    void newPaper(Paper paper);

    List<PaperForm> findAllPaperForm();

    /**
     * 根据模版id删除模版
     * @param id
     */
    void delPaperFormById(Integer id);

    /**
     * 根据该学生的专业班级查找未开始的试卷
     * @param major
     * @return
     */
    List<Paper> findPaperListByMajor(String major);

    /**
     * 根据专业班级查找模拟试卷
     * @param major
     * @return
     */
    List<Paper> findPracticePapersByMajor(String major);

    /**
     * 根据paperId和试题类型查找该类型题目集合
     * @param paperId
     * @param qChoiceType
     * @return
     */
    Set<Question> findQuestionsByPaperIdAndType(Integer paperId, Integer qChoiceType);

    /**
     * 教师批改试卷
     * @param stuId
     * @param paperId
     * @param request
     * @return 返回批改后的分数
     */
    String markPaper(Integer stuId, Integer paperId, HttpServletRequest request);

    /**
     * 根据教师id查找未开始考试的试卷
     * @param id
     * @return
     */
    List<Paper> findUnDoPaperListByTeacherId(Integer id);

    /**
     * 修改考试时间
     * @param id
     * @param beginTime
     * @param endTime
     */
    void editPaperById(Integer id, String beginTime, String endTime, String allowTime);

    /**
     * 根据老师Id查找已结束试卷
     * @param teacherId
     * @return
     */
    List<Paper> findDonePaperListByTeacherId(Object teacherId);


    /**
     * 根据学生和试卷查找复查试题记录
     * @param stuNumber
     * @param paperId
     * @return
     */
    List<StuAnswerRecord> findAnswerRecordByStuAndPaper(String stuNumber, String paperId);

    Major findMajorById(Integer id);

    /**
     * 改变试卷状态未已完成
     * @param id
     */
    void changeStateById(Integer id);

    /**
     * 删除试卷
     * @param id
     */
    void delPaperById(Integer id);
}

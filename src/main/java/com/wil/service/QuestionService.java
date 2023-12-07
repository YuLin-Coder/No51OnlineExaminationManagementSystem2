package com.wil.service;

import com.github.pagehelper.PageInfo;
import com.wil.entity.Course;
import com.wil.entity.Question;
import com.wil.entity.StuAnswerRecord;
import com.wil.entity.Type;

import java.util.List;
import java.util.Map;

/**
 * Created by wil on 2018/5/10.
 */
public interface QuestionService {
    /**
     * 显示试题库并分页
     * @param pageNo
     * @return
     */
    PageInfo<Question> pageForQuestionList(Integer pageNo);

    Question findById(Integer id);

    /**
     * 查找试题所属课程
     * @param courseId
     * @return
     */
    Course findByCourseId(Integer courseId);

    /**
     * 查找所有的试题类型
     * @return
     */
    List<Type> findAllType();

    /**
     * 根据教师id查找他教的所有课程（可以出题的课程）
     * @param teacherId
     * @return
     */
    List<Course> findTeacherCourse(Integer teacherId);

    /**
     * 新增试题
     * @param question
     */
    void saveNewQuestion(Question question);

    /**
     * 修改试题
     * @param id
     * @param questionName
     * @param answer
     */
    void editQuestion(Integer id, String questionName, String answer, String remark);

    void deleteQuestion(Integer id);

    /**
     * 根据课程id和题目类型获取所有该类型的题目id集合
     * @param typeId
     * @return
     */
    public List<Integer> getIdList(Integer typeId, Integer courseId);

    /**
     * 根据答案记录集合查找试题集合
     * @param answerRecordList
     * @return
     */
    List<Question> findByAnswerRecordList(List<StuAnswerRecord> answerRecordList);

    /**
     * 整理出学生答案 以Map<问题、答案>的形式
     * @param answerRecordList
     * @param questionList
     * @return
     */
    Map<String,String> findMapByStuAnswerRecordAndQuestionList(List<StuAnswerRecord> answerRecordList, List<Question> questionList);
}

package com.wil.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wil.entity.*;
import com.wil.exception.ServiceException;
import com.wil.mapper.CourseMapper;
import com.wil.mapper.QuestionMapper;
import com.wil.mapper.TypeMapper;
import com.wil.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wil on 2018/5/10.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private TypeMapper typeMapper;


    /**
     * 显示试题库并分页
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<Question> pageForQuestionList(Integer pageNo) {
        PageHelper.startPage(pageNo, 8);
        List<Question> questionList = questionMapper.selectByExample(new QuestionExample());
        return new PageInfo<Question>(questionList);
    }

    @Override
    public Question findById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找试题所属课程
     * @param courseId
     * @return
     */
    @Override
    public Course findByCourseId(Integer courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    /**
     * 查找所有的试题类型
     * @return
     */
    @Override
    public List<Type> findAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }

    /**
     * 根据教师id查找他教的所有课程（可以出题的课程）
     * @param teacherId
     * @return
     */
    @Override
    public List<Course> findTeacherCourse(Integer teacherId) {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andTeacherIdEqualTo(teacherId);
        return courseMapper.selectByExample(courseExample);
    }

    @Override
    public void saveNewQuestion(Question question) {
        questionMapper.insertSelective(question);
    }

    @Override
    public void editQuestion(Integer id, String questionName, String answer, String remark) throws ServiceException{
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question != null) {
            question.setQuestionName(questionName);
            question.setAnswer(answer);
            question.setRemark(remark);
            questionMapper.updateByPrimaryKeySelective(question);
        } else {
            throw new ServiceException("试题不存在");
        }

    }

    @Override
    public void deleteQuestion(Integer id) {
        questionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据课程id和题目类型获取所有该类型的题目id集合
     * @param typeId
     * @return
     */
    public List<Integer> getIdList(Integer typeId, Integer courseId) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andTypeIdEqualTo(typeId).andCourseIdEqualTo(courseId);
        List<Question> questionList = questionMapper.selectByExample(questionExample);
        List<Integer> idList = new ArrayList<>();
        for(Question q : questionList) {
            Integer id = q.getId();
            idList.add(id);
        }
        return idList;
    }

    /**
     * 根据答案记录集合查找试题集合
     * @param answerRecordList
     * @return
     */
    @Override
    public List<Question> findByAnswerRecordList(List<StuAnswerRecord> answerRecordList) throws ServiceException {
        if(answerRecordList == null) {
            throw new ServiceException("未找到主观题答案记录！");
        }

        List<Question> questionList = new ArrayList<>();
        for(StuAnswerRecord answerRecord : answerRecordList) {
            Question question = questionMapper.selectByPrimaryKey(answerRecord.getQuestionId());
            questionList.add(question);
        }

        return questionList;
    }

    /**
     * 整理出学生答案 以Map<问题、答案>的形式
     * @param answerRecordList
     * @param questionList
     * @return
     */
    @Override
    public Map<String, String> findMapByStuAnswerRecordAndQuestionList(List<StuAnswerRecord> answerRecordList, List<Question> questionList) {
        Map<String, String> resMap = new HashMap<>();
        for(Question question : questionList) {
            for(StuAnswerRecord s : answerRecordList) {
                if(question.getId() == s.getQuestionId()) {
                    resMap.put(question.getQuestionName(), s.getAnswer());
                }
            }
        }
        return resMap;
    }
}

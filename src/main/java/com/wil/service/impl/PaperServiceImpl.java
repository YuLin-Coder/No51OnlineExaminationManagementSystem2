package com.wil.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wil.entity.*;
import com.wil.exception.ServiceException;
import com.wil.mapper.*;
import com.wil.service.PaperService;
import com.wil.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.JaccardSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wil on 2018/5/11.
 */
@Service
public class PaperServiceImpl implements PaperService {

    private Logger logger = LoggerFactory.getLogger(PaperServiceImpl.class);

    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private PaperFormMapper paperFormMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StuAnswerRecordMapper stuAnswerRecordMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    private static final Integer qChoiceType = 1;
    private static final Integer qMulChoiceType = 2;
    private static final Integer qTofType = 3;
    private static final Integer qFillType = 4;
    private static final Integer qSaqType = 5;
    private static final Integer qProgramType = 6;

    private static final String PAPER_TYPE_PRACTICE = "模拟";
    private static final String PAPER_TYPE_FORMAL = "正式";

    private static final String PAPER_STATE_START = "未开始";
    private static final String PAPER_STATE_END = "已结束";

    /**
     * 查找该老师所有试卷并分页
     * @param teacherId
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<Paper> pageForPaperList(Integer teacherId, Integer pageNo) {
        PaperExample paperExample = new PaperExample();
        paperExample.createCriteria().andTeacherIdEqualTo(teacherId);
        PageHelper.startPage(pageNo, 8);
        List<Paper> paperList = paperMapper.selectByExample(paperExample);
        return new PageInfo<Paper>(paperList);
    }

    @Override
    public Paper findById(Integer id) {
        return paperMapper.selectByPrimaryKey(id);
    }

    @Override
    public Course findCourseById(Integer courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public void newPaperForm(PaperForm paperForm) {
        paperFormMapper.insertSelective(paperForm);
    }

    /**
     * 查找一个老师所教的所有课程
     * @param teacherId
     * @return
     */
    @Override
    public List<Course> findCourseListByTeacherId(Integer teacherId) {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andTeacherIdEqualTo(teacherId);
        return courseMapper.selectByExample(courseExample);
    }

    @Override
    public Major findMajorByMajorName(String major) {
        return majorMapper.findByName(major);
    }

    /**
     * 组卷
     * @param paper
     */
    @Override
    public void newPaper(Paper paper) {
        Integer paperFormId = paper.getPaperFormId();
        PaperForm paperForm = paperFormMapper.selectByPrimaryKey(paperFormId);
        Integer courseId = paper.getCourseId();

        //根据各种题型的数量从试题库中抽取题目

        String qChoiceNum = paperForm.getqChoiceNum();
        String qMulChoiceNum = paperForm.getqMulChoiceNum();
        String qTofNum = paperForm.getqTofNum();
        String qFillNum = paperForm.getqFillNum();
        String qSaqNum = paperForm.getqSaqNum();
        String qProgramNum = paperForm.getqProgramNum();

        List<Integer> paperQuestionIdList = new ArrayList<>();

        getPaperQuestionIdList(qChoiceNum, paperQuestionIdList, qChoiceType, courseId);

        getPaperQuestionIdList(qMulChoiceNum, paperQuestionIdList, qMulChoiceType, courseId);

        getPaperQuestionIdList(qTofNum, paperQuestionIdList, qTofType, courseId);

        getPaperQuestionIdList(qFillNum, paperQuestionIdList, qFillType, courseId);

        getPaperQuestionIdList(qSaqNum, paperQuestionIdList, qSaqType, courseId);

        getPaperQuestionIdList(qProgramNum, paperQuestionIdList, qProgramType, courseId);

        StringBuilder builder = new StringBuilder();
        for(Integer id : paperQuestionIdList) {
            String idString = String.valueOf(id);
            builder.append(idString);
            builder.append(",");
        }
        String ids = builder.toString();
        paper.setQuestionId(ids.substring(0, ids.length()-1));

        paperMapper.insertSelective(paper);

    }

    @Override
    public List<PaperForm> findAllPaperForm() {
        return paperFormMapper.selectByExample(new PaperFormExample());
    }

    /**
     * 根据模版id删除模版
     * @param id
     */
    @Override
    public void delPaperFormById(Integer id) throws ServiceException{
        PaperForm form = paperFormMapper.selectByPrimaryKey(id);
        if(form == null) {
            throw new ServiceException("试卷模版不存在!");
        }

        //查找是否有正在使用该模版的试卷，如果有，则不允许删除模版
        PaperExample paperExample = new PaperExample();
        paperExample.createCriteria().andPaperFormIdEqualTo(id);
        List<Paper> paperList = paperMapper.selectByExample(paperExample);
        if(paperList != null && paperList.size() != 0) {
            throw new ServiceException("试卷模版正在使用，不能删除该模版！");
        }
        paperFormMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据该学生的专业班级查找正式试卷
     * @param majorName
     * @return
     */
    @Override
    public List<Paper> findPaperListByMajor(String majorName) {
        Major major = majorMapper.findByName(majorName);
        if(major == null) {
            throw new ServiceException("专业班级不存在");
        }

        PaperExample paperExample = new PaperExample();
        paperExample.createCriteria().andMajorIdEqualTo(major.getId())
        .andPaperTypeEqualTo(PAPER_TYPE_FORMAL);
        List<Paper> paperList = paperMapper.selectByExample(paperExample);

        return paperList;
    }

    /**
     * 根据专业班级查找模拟试卷
     * @param majorName
     * @return
     */
    @Override
    public List<Paper> findPracticePapersByMajor(String majorName) {

        Major major = majorMapper.findByName(majorName);
        if(major == null) {
            throw new ServiceException("专业班级不存在");
        }

        PaperExample paperExample = new PaperExample();

        paperExample.createCriteria().andMajorIdEqualTo(major.getId())
                .andPaperTypeEqualTo(PAPER_TYPE_PRACTICE);
        return paperMapper.selectByExample(paperExample);
    }

    /**
     * 根据paperId和试题类型查找该类型题目集合
     * @param paperId
     * @param qChoiceType
     * @return
     */
    @Override
    public Set<Question> findQuestionsByPaperIdAndType(Integer paperId, Integer qChoiceType) {
        Paper paper = paperMapper.selectByPrimaryKey(paperId);
        String qIds = paper.getQuestionId();
        String[] qIdArray = qIds.split(",");
        Set<Question> questionSet = new HashSet<>();
        for(String id : qIdArray) {
            Question question = questionMapper.selectByPrimaryKey(Integer.parseInt(id));
            if(qChoiceType.equals(question.getTypeId())) {
                questionSet.add(question);
            }
        }
        return questionSet;
    }

    /**
     * 教师批改试卷
     * @param stuId
     * @param paperId
     * @param request
     */
    @Override
    @Transactional
    public String markPaper(Integer stuId, Integer paperId, HttpServletRequest request) throws ServiceException{

        Paper paper = paperMapper.selectByPrimaryKey(paperId);
        PaperForm paperForm = paperFormMapper.selectByPrimaryKey(paper.getPaperFormId());

        Set<Question> qChoiceList =findQuestionsByPaperIdAndType(paperId, qChoiceType);
        Set<Question> qMulChoiceList = findQuestionsByPaperIdAndType(paperId, qMulChoiceType);
        Set<Question> qTofList = findQuestionsByPaperIdAndType(paperId, qTofType);
        Set<Question> qFillList = findQuestionsByPaperIdAndType(paperId, qFillType);
        Set<Question> qSaqList = findQuestionsByPaperIdAndType(paperId, qSaqType);
        Set<Question> qProgramList = findQuestionsByPaperIdAndType(paperId, qProgramType);

        Integer qChoiceScore = transStringToInteger(paperForm.getqChoiceScore());
        Integer qMulChoiceScore = transStringToInteger(paperForm.getqMulChoiceScore());
        Integer qTofScore = transStringToInteger(paperForm.getqTofScore());
        Integer qFillScore = transStringToInteger(paperForm.getqFillScore());
        Double qSaqScore = transStringToDouble(paperForm.getqSaqScore());
        Double qProgramScore = transStringToDouble(paperForm.getqProgramScore());

        List<String> wrongIds = new ArrayList<>();
        Integer score = 0;

        //单选题批改
        if(qChoiceList != null && qChoiceList.size() != 0) {
            for(Question q : qChoiceList) {
                String res = request.getParameter(String.valueOf(q.getId()));
                if(q.getAnswer().equals(res)) {
                    score += qChoiceScore;
                } else {
                    wrongIds.add(String.valueOf(q.getId()));
                }
            }
        }

        //多选题批改
        if(qMulChoiceList != null && qMulChoiceList.size() != 0) {
            for(Question q : qMulChoiceList) {
                String[] res = request.getParameterValues(String.valueOf(q.getId()));
                //没有选答案就给零分
                if(res != null) {
                    String result = "";
                    for(String s : res) {
                        result = s + ",";
                    }
                    result = result.substring(0,result.length()-1);
                    if(result.equals(q.getAnswer())) {
                        score += qMulChoiceScore;
                    } else {
                        wrongIds.add(String.valueOf(q.getId()));
                    }
                } else {
                    wrongIds.add(String.valueOf(q.getId()));
                }
            }
        }

        //判断题批改
        if(qTofList != null && qTofList.size() != 0) {
            for(Question q : qTofList) {
                String res = request.getParameter(String.valueOf(q.getId()));
                if(q.getAnswer().equals(res)) {
                    score += qTofScore;
                } else {
                    wrongIds.add(String.valueOf(q.getId()));
                }
            }
        }

        //填空题批改
        if(qFillList != null && qFillList.size() != 0) {
            for(Question q : qFillList) {
                String res = request.getParameter(String.valueOf(q.getId()));
                if(q.getAnswer().equals(res)) {
                    score += qFillScore;
                } else {
                    wrongIds.add(String.valueOf(q.getId()));
                }
            }
        }

        JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();
        //是NumberFormat的一个具体子类，用于格式化十进制数字，此处用于将数字转化为String
        //#表示整数
        DecimalFormat df = new DecimalFormat("#");
        StuAnswerRecord stuAnswerRecord = null;
        //简答题批改
        if(qSaqList != null && qSaqList.size() != 0) {
            double f = 0;

            for(Question q : qSaqList) {

                wrongIds.add(String.valueOf(q.getId()));

                String res = request.getParameter(String.valueOf(q.getId()));
                String answer = q.getAnswer();

                //计算jaccard相似系数
                double jcdSimilarity = jaccardSimilarity.apply(res, answer);

                double s = jcdSimilarity * qSaqScore;
                f += s;

                //存入数据库
                stuAnswerRecord = new StuAnswerRecord(paperId, stuId, q.getId(), res);
                stuAnswerRecordMapper.insertSelective(stuAnswerRecord);

            }

            String qSaqResScore = df.format(f);
            score += Integer.parseInt(qSaqResScore);
        }

        //编程题批改
        if(qProgramList != null && qProgramList.size() != 0) {
            double f = 0;

            for(Question q : qProgramList) {
                String res = request.getParameter(String.valueOf(q.getId()));
                String answer = q.getAnswer();

                //计算jaccard相似系数
                double jcdSimilarity = jaccardSimilarity.apply(res, answer);

                double s = jcdSimilarity * qProgramScore;
                f += s;

                //存入数据库
                stuAnswerRecord = new StuAnswerRecord(paperId, stuId, q.getId(), res);
                stuAnswerRecordMapper.insertSelective(stuAnswerRecord);
            }

            String qProgramResScore = df.format(f);
            score += Integer.parseInt(qProgramResScore);
        }

        StringBuilder builder = new StringBuilder();
        for(String id : wrongIds) {
            builder.append(id);
            builder.append(",");
        }
        String wrong = builder.toString();
        String wrongResIds = wrong.substring(0, wrong.length()-1);

        Score scoreObj = new Score(stuId, paperId, paper.getPaperName(), String.valueOf(score), wrongResIds);
        scoreMapper.insertSelective(scoreObj);

        return String.valueOf(score);

    }

    private Integer transStringToInteger(String s) {
        if(StringUtils.isNotEmpty(s)) {
            return Integer.parseInt(s);
        } else {
            return 0;
        }
    }

    private Double transStringToDouble(String s) {
        if(StringUtils.isNotEmpty(s)) {
            return Double.valueOf(s);
        } else {
            return 0.0;
        }
    }

    /**
     * 根据教师id查找未开始考试的试卷
     * @param id
     * @return
     */
    @Override
    public List<Paper> findUnDoPaperListByTeacherId(Integer id) {
        PaperExample paperExample = new PaperExample();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = simpleDateFormat.format(new Date());

        paperExample.createCriteria().andTeacherIdEqualTo(id)
                .andPaperTypeEqualTo(PAPER_TYPE_FORMAL)
                .andBeginTimeGreaterThan(now);

        return paperMapper.selectByExample(paperExample);
    }

    /**
     * 修改考试时间
     * @param id
     * @param beginTime
     * @param endTime
     */
    @Override
    public void editPaperById(Integer id, String beginTime, String endTime, String allowTime) {
        Paper paper = paperMapper.selectByPrimaryKey(id);
        paper.setBeginTime(beginTime);
        paper.setEndTime(endTime);
        paper.setAllowTime(allowTime);
        paperMapper.updateByPrimaryKeySelective(paper);

    }

    /**
     * 根据老师Id查找已结束试卷
     * @param teacherId
     * @return
     */
    @Override
    public List<Paper> findDonePaperListByTeacherId(Object teacherId) {
        PaperExample paperExample = new PaperExample();
        paperExample.createCriteria().andPaperStateEqualTo("已结束");
        return paperMapper.selectByExample(paperExample);
    }

    /**
     * 根据学生和试卷查找复查试题记录
     * @param stuNumber
     * @param paperId
     * @return
     */
    @Override
    public List<StuAnswerRecord> findAnswerRecordByStuAndPaper(String stuNumber, String paperId) throws ServiceException {
        Student student = studentMapper.findByStuNumber(stuNumber);
        if(student == null) {
            throw new ServiceException("该学号不存在！");
        }

        StuAnswerRecordExample answerRecordExample = new StuAnswerRecordExample();
        answerRecordExample.createCriteria().andStuIdEqualTo(student.getId())
                .andPaperIdEqualTo(Integer.parseInt(paperId));
        return stuAnswerRecordMapper.selectByExample(answerRecordExample);
    }

    @Override
    public Major findMajorById(Integer id) {
        return majorMapper.selectByPrimaryKey(id);
    }

    /**
     * 改变试卷状态未已完成
     * @param id
     */
    @Override
    public void changeStateById(Integer id) {
        Paper paper = paperMapper.selectByPrimaryKey(id);
        paper.setPaperState(PAPER_STATE_END);
        paperMapper.updateByPrimaryKeySelective(paper);
    }

    /**
     * 删除试卷,删除与试卷有关的表中的数据：score,stu_answer_record
     * @param id
     */
    @Override
    @Transactional
    public void delPaperById(Integer id) {
        Paper paper = paperMapper.selectByPrimaryKey(id);


        if(paper != null) {
            Teacher teacher = teacherMapper.selectByPrimaryKey(paper.getTeacherId());

            //删除score表中paperId为传入参数的对象
            ScoreExample scoreExample = new ScoreExample();
            scoreExample.createCriteria().andPaperIdEqualTo(id);
            List<Score> scoreList = scoreMapper.selectByExample(scoreExample);

            for(Score s : scoreList) {
                scoreMapper.deleteByPrimaryKey(s.getId());

            }

            //删除stu_answer_record
            StuAnswerRecordExample stuAnswerRecordExample = new StuAnswerRecordExample();
            stuAnswerRecordExample.createCriteria().andPaperIdEqualTo(id);
            List<StuAnswerRecord> stuAnswerRecordList = stuAnswerRecordMapper.selectByExample(stuAnswerRecordExample);

            for(StuAnswerRecord answerRecord : stuAnswerRecordList) {
                stuAnswerRecordMapper.deleteByPrimaryKey(answerRecord.getId());
            }

            //删除试卷
            paperMapper.deleteByPrimaryKey(id);
            logger.info("教师：{} 删除了试卷：{}",teacher.getName(), paper.getPaperName());

        }
    }


    private void getPaperQuestionIdList(String varQuestionTypeNum,
                                        List<Integer> paperQuestionIdList,
                                        Integer questionType, Integer courseId) {
        Integer num;
        if(StringUtils.isNotEmpty(varQuestionTypeNum)) {
            num = Integer.parseInt(varQuestionTypeNum);
            List<Integer> idList = questionService.getIdList(questionType, courseId);
            List<Integer> qChoiceIdList = getRandomIdList(idList, num);
            paperQuestionIdList.addAll(qChoiceIdList);
        }
    }

    /**
     * 从所需题型中随机抽出固定数量的题
     * @param all 题库中该题型所有的题目的id集合
     * @param num 题目数量
     * @return
     */
    private List<Integer> getRandomIdList(List<Integer> all, Integer num) throws ServiceException {
        Random random = new Random();
        List<Integer> result = new ArrayList<>();
        int index = 0;
        for(int i = 0; i < num; i++) {
            try {
                index = random.nextInt(all.size() - 1);
                result.add(all.get(index));
                all.remove(index);
            } catch (Exception e) {
                throw new ServiceException("试题数量不足，组卷失败！");
            }
        }
        return result;
    }
}

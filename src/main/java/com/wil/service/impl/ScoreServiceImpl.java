package com.wil.service.impl;

import com.wil.entity.*;
import com.wil.exception.ServiceException;
import com.wil.mapper.CourseMapper;
import com.wil.mapper.MajorMapper;
import com.wil.mapper.PaperMapper;
import com.wil.mapper.ScoreMapper;
import com.wil.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wil on 2018/5/17.
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Score> findByStuId(Integer id) {
        ScoreExample scoreExample = new ScoreExample();
        scoreExample.createCriteria().andStuIdEqualTo(id);

        return scoreMapper.selectByExample(scoreExample);
    }

    /**
     * 根据课程统计成绩信息
     * @param courseId
     * @return
     */
    @Override
    public List<Map<String, Object>> countByCourse(String courseId) {

        PaperExample paperExample = new PaperExample();
        paperExample.createCriteria().andCourseIdEqualTo(Integer.parseInt(courseId))
                .andPaperTypeEqualTo("正式");
        List<Paper> paperList = paperMapper.selectByExample(paperExample);

        Map<String, Object> countMap = new HashMap<>();
        List<Map<String, Object>> countList = new ArrayList<>();

        countPerScoreNum(paperList, countMap, countList);
        return countList;
    }

    /**
     * 根据专业和课程统计成绩
     * @param majorName
     * @param courseId
     * @return
     */
    @Override
    public List<Map<String, Object>> countByMajorAndCourse(String majorName, Integer courseId) {
        Major major = majorMapper.findByName(majorName);
        if(major == null) {
            throw new ServiceException("专业不存在！");
        }
        PaperExample paperExample = new PaperExample();
        paperExample.createCriteria().andMajorIdEqualTo(major.getId()).andCourseIdEqualTo(courseId)
                .andPaperTypeEqualTo("正式");
        List<Paper> paperList = paperMapper.selectByExample(paperExample);

        Map<String, Object> countMap = new HashMap<>();
        List<Map<String, Object>> countList = new ArrayList<>();


        countPerScoreNum(paperList, countMap, countList);
        return countList;
    }

    /**
     * 统计该学生本学期所有课程的平均成绩
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> averageScore(Integer id) {
        ScoreExample scoreExample = new ScoreExample();
        scoreExample.createCriteria().andStuIdEqualTo(id);
        List<Score> scoreList = scoreMapper.selectByExample(scoreExample);

        Map<String, Object> avgMap = new HashMap<>();
        Map<String, Object> myScoreMap = new HashMap<>();
        List<Map<String, Object>> avgList = new ArrayList<>();

        //统计每门课程平均成绩
        for(Score s : scoreList) {
            double avg = scoreMapper.avgScoreByPaperId(s.getPaperId());
            Paper paper = paperMapper.selectByPrimaryKey(s.getPaperId());
            Course course = courseMapper.selectByPrimaryKey(paper.getCourseId());
            avgMap.put(course.getCourseName(), String.valueOf(avg));
        }

        //统计我的每门课程成绩
        for(Score s : scoreList) {
            String scoreMy = s.getScore();
            Paper paper = paperMapper.selectByPrimaryKey(s.getPaperId());
            Course course = courseMapper.selectByPrimaryKey(paper.getCourseId());
            myScoreMap.put(course.getCourseName(), scoreMy);
        }

        avgList.add(avgMap);
        avgList.add(myScoreMap);

        return avgList;
    }

    /**
     * 根据分数段统计该生课程门数和具体课程
     * @param studentId
     * @return
     */
    @Override
    public List<Map<String, Object>> countByLevel(Object studentId) {
        ScoreExample scoreExample = new ScoreExample();
        scoreExample.createCriteria().andStuIdEqualTo((Integer) studentId);
        List<Score> scoreList = scoreMapper.selectByExample(scoreExample);

        long eNum = 0L;
        long dNum = 0L;
        long cNum = 0L;
        long bNum = 0L;
        long aNum = 0L;

        StringBuilder sbe = new StringBuilder();
        StringBuilder sbd = new StringBuilder();
        StringBuilder sbc = new StringBuilder();
        StringBuilder sbb = new StringBuilder();
        StringBuilder sba = new StringBuilder();

        Map<String, Object> scoreMap = new HashMap<>();
        Map<String, Object> levelPaperMap = new HashMap<>();
        List<Map<String, Object>> resList = new ArrayList<>();

        for(Score s : scoreList) {

            Integer score = Integer.parseInt(s.getScore());

            if(score < 60) {
                eNum++;
                sbe.append("[" + s.getPaperName() + "] ");

            } else if(score < 70) {
                bNum++;
                sbd.append("[" + s.getPaperName() + "] ");
            } else if(score < 80) {
                cNum++;
                sbc.append("[" + s.getPaperName() + "] ");
            } else if(score < 90) {
                bNum++;
                sbb.append("[" + s.getPaperName() + "] ");
            } else {
                aNum++;
                sba.append("[" + s.getPaperName() + "] ");
            }

        }

        scoreMap.put("60分以下", eNum);
        scoreMap.put("60-70分", dNum);
        scoreMap.put("70-80分", cNum);
        scoreMap.put("80-90分", bNum);
        scoreMap.put("90分以上", aNum);

        levelPaperMap.put("60分以下",sbe.toString());
        levelPaperMap.put("60-70分",sbd.toString());
        levelPaperMap.put("70-80分",sbc.toString());
        levelPaperMap.put("80-90分",sbb.toString());
        levelPaperMap.put("90分以上",sba.toString());

        resList.add(scoreMap);
        resList.add(levelPaperMap);
        return resList;
    }

    /**
     * 统计各个分数段人数
     * @param paperList
     * @param countMap
     * @param countList
     */
    private void countPerScoreNum(List<Paper> paperList, Map<String, Object> countMap, List<Map<String, Object>> countList) {
        ScoreExample scoreExample;
        long eNum = 0L;
        long dNum = 0L;
        long cNum = 0L;
        long bNum = 0L;
        long aNum = 0L;

        if(paperList != null && paperList.size() != 0) {
            for(Paper p : paperList) {
                Integer id = p.getId();
                //不及格人数 E
                scoreExample = new ScoreExample();
                scoreExample.createCriteria().andPaperIdEqualTo(id).andScoreLessThan("60");
                eNum += scoreMapper.countByExample(scoreExample);
                countMap.put("60分以下", eNum);

                //60到70 D
                scoreExample = new ScoreExample();
                scoreExample.createCriteria().andPaperIdEqualTo(id).andScoreBetween("60", "70");
                dNum += scoreMapper.countByExample(scoreExample);
                countMap.put("60-70分", dNum);

                //70到80 C
                scoreExample = new ScoreExample();
                scoreExample.createCriteria().andPaperIdEqualTo(id).andScoreBetween("70", "80");
                cNum += scoreMapper.countByExample(scoreExample);
                countMap.put("70-80分", cNum);

                //80到90 B
                scoreExample = new ScoreExample();
                scoreExample.createCriteria().andPaperIdEqualTo(id).andScoreBetween("80", "90");
                bNum += scoreMapper.countByExample(scoreExample);
                countMap.put("80-90分", bNum);

                //90到100 A
                scoreExample = new ScoreExample();
                scoreExample.createCriteria().andPaperIdEqualTo(id).andScoreGreaterThan("90");
                aNum += scoreMapper.countByExample(scoreExample);
                countMap.put("90分以上", aNum);
            }
            countList.add(countMap);
        }
    }


}

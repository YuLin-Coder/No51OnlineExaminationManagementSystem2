package com.wil.service;

import com.wil.entity.Score;

import java.util.List;
import java.util.Map;

/**
 * Created by wil on 2018/5/17.
 */
public interface ScoreService {


    List<Score> findByStuId(Integer id);

    /**
     * 根据课程统计成绩信息
     * @param courseId
     * @return
     */
    List<Map<String,Object>> countByCourse(String courseId);

    /**
     * 根据专业和课程统计成绩
     * @param majorName
     * @param id
     * @return
     */
    List<Map<String,Object>> countByMajorAndCourse(String majorName, Integer id);

    /**
     * 统计该学生本学期所有课程的平均成绩
     * @param id
     * @return
     */
    List<Map<String,Object>> averageScore(Integer id);

    /**
     * 根据分数段统计该生课程门数和具体课程
     * @param studentId
     * @return
     */
    List<Map<String,Object>> countByLevel(Object studentId);
}

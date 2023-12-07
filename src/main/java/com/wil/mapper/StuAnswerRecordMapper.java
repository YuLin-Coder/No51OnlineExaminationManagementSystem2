package com.wil.mapper;

import com.wil.entity.StuAnswerRecord;
import com.wil.entity.StuAnswerRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuAnswerRecordMapper {
    long countByExample(StuAnswerRecordExample example);

    int deleteByExample(StuAnswerRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StuAnswerRecord record);

    int insertSelective(StuAnswerRecord record);

    List<StuAnswerRecord> selectByExample(StuAnswerRecordExample example);

    StuAnswerRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StuAnswerRecord record, @Param("example") StuAnswerRecordExample example);

    int updateByExample(@Param("record") StuAnswerRecord record, @Param("example") StuAnswerRecordExample example);

    int updateByPrimaryKeySelective(StuAnswerRecord record);

    int updateByPrimaryKey(StuAnswerRecord record);
}
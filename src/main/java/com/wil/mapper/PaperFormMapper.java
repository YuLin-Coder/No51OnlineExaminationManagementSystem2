package com.wil.mapper;

import com.wil.entity.PaperForm;
import com.wil.entity.PaperFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaperFormMapper {
    long countByExample(PaperFormExample example);

    int deleteByExample(PaperFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PaperForm record);

    int insertSelective(PaperForm record);

    List<PaperForm> selectByExample(PaperFormExample example);

    PaperForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PaperForm record, @Param("example") PaperFormExample example);

    int updateByExample(@Param("record") PaperForm record, @Param("example") PaperFormExample example);

    int updateByPrimaryKeySelective(PaperForm record);

    int updateByPrimaryKey(PaperForm record);
}
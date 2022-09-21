package com.example.mapper;

import com.example.model.Cup;
import com.example.model.CupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CupMapper {
    long countByExample(CupExample example);

    int deleteByExample(CupExample example);

    int deleteByPrimaryKey(String id);

    int insert(Cup record);

    int insertSelective(Cup record);

    List<Cup> selectByExample(CupExample example);

    Cup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Cup record, @Param("example") CupExample example);

    int updateByExample(@Param("record") Cup record, @Param("example") CupExample example);

    int updateByPrimaryKeySelective(Cup record);

    int updateByPrimaryKey(Cup record);
}
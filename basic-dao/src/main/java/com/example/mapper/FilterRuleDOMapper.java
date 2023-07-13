package com.example.mapper;

import com.example.model.FilterRuleDO;
import com.example.model.FilterRuleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilterRuleDOMapper {
    long countByExample(FilterRuleDOExample example);

    int deleteByExample(FilterRuleDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FilterRuleDO record);

    int insertSelective(FilterRuleDO record);

    List<FilterRuleDO> selectByExample(FilterRuleDOExample example);

    FilterRuleDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FilterRuleDO record, @Param("example") FilterRuleDOExample example);

    int updateByExample(@Param("record") FilterRuleDO record, @Param("example") FilterRuleDOExample example);

    int updateByPrimaryKeySelective(FilterRuleDO record);

    int updateByPrimaryKey(FilterRuleDO record);
}
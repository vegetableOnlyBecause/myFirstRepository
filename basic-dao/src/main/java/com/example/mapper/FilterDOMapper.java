package com.example.mapper;

import com.example.model.FilterDO;
import com.example.model.FilterDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilterDOMapper {
    long countByExample(FilterDOExample example);

    int deleteByExample(FilterDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FilterDO record);

    int insertSelective(FilterDO record);

    List<FilterDO> selectByExample(FilterDOExample example);

    FilterDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FilterDO record, @Param("example") FilterDOExample example);

    int updateByExample(@Param("record") FilterDO record, @Param("example") FilterDOExample example);

    int updateByPrimaryKeySelective(FilterDO record);

    int updateByPrimaryKey(FilterDO record);
}
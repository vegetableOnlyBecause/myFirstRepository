package com.example.mapper;

import com.example.model.GoodDO;
import com.example.model.GoodDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodDOMapper {
    long countByExample(GoodDOExample example);

    int deleteByExample(GoodDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodDO record);

    int insertSelective(GoodDO record);

    List<GoodDO> selectByExample(GoodDOExample example);

    GoodDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodDO record, @Param("example") GoodDOExample example);

    int updateByExample(@Param("record") GoodDO record, @Param("example") GoodDOExample example);

    int updateByPrimaryKeySelective(GoodDO record);

    int updateByPrimaryKey(GoodDO record);
}
package com.example.mapper;

import com.example.model.TypeDO;
import com.example.model.TypeDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeDOMapper {
    long countByExample(TypeDOExample example);

    int deleteByExample(TypeDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TypeDO record);

    int insertSelective(TypeDO record);

    List<TypeDO> selectByExample(TypeDOExample example);

    TypeDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TypeDO record, @Param("example") TypeDOExample example);

    int updateByExample(@Param("record") TypeDO record, @Param("example") TypeDOExample example);

    int updateByPrimaryKeySelective(TypeDO record);

    int updateByPrimaryKey(TypeDO record);
}
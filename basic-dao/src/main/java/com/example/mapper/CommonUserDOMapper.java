package com.example.mapper;

import com.example.model.CommonUserDO;
import com.example.model.CommonUserDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonUserDOMapper {
    long countByExample(CommonUserDOExample example);

    int deleteByExample(CommonUserDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommonUserDO record);

    int insertSelective(CommonUserDO record);

    List<CommonUserDO> selectByExample(CommonUserDOExample example);

    CommonUserDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommonUserDO record, @Param("example") CommonUserDOExample example);

    int updateByExample(@Param("record") CommonUserDO record, @Param("example") CommonUserDOExample example);

    int updateByPrimaryKeySelective(CommonUserDO record);

    int updateByPrimaryKey(CommonUserDO record);

}
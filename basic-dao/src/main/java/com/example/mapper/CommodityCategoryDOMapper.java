package com.example.mapper;

import com.example.model.CommodityCategoryDO;
import com.example.model.CommodityCategoryDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityCategoryDOMapper {
    long countByExample(CommodityCategoryDOExample example);

    int deleteByExample(CommodityCategoryDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommodityCategoryDO record);

    int insertSelective(CommodityCategoryDO record);

    List<CommodityCategoryDO> selectByExample(CommodityCategoryDOExample example);

    CommodityCategoryDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommodityCategoryDO record, @Param("example") CommodityCategoryDOExample example);

    int updateByExample(@Param("record") CommodityCategoryDO record, @Param("example") CommodityCategoryDOExample example);

    int updateByPrimaryKeySelective(CommodityCategoryDO record);

    int updateByPrimaryKey(CommodityCategoryDO record);
}
package com.example.mapper;

import com.example.model.CommodityDO;
import com.example.model.CommodityDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityDOMapper {
    long countByExample(CommodityDOExample example);

    int deleteByExample(CommodityDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommodityDO record);

    int insertSelective(CommodityDO record);

    List<CommodityDO> selectByExample(CommodityDOExample example);

    CommodityDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommodityDO record, @Param("example") CommodityDOExample example);

    int updateByExample(@Param("record") CommodityDO record, @Param("example") CommodityDOExample example);

    int updateByPrimaryKeySelective(CommodityDO record);

    int updateByPrimaryKey(CommodityDO record);
}
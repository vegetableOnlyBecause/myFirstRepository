package com.example.mapper;

import com.example.model.OrderDO;
import com.example.model.OrderDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDOMapper {
    long countByExample(OrderDOExample example);

    int deleteByExample(OrderDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    List<OrderDO> selectByExample(OrderDOExample example);

    OrderDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    int updateByExample(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}
package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.CartDO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDOMapper extends BaseMapper<CartDO> {
}
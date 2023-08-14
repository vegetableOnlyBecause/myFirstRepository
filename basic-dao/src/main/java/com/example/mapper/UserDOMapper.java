package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDOMapper extends BaseMapper<UserDO> {
}
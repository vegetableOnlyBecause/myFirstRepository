package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.constant.RedisKeyConstants;
import com.example.common.utils.RedisUtils;
import com.example.dao.UserDao;
import com.example.mapper.UserDOMapper;
import com.example.model.UserDO;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @title: 用户查询类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 14:19
 * @description:
 */
@Repository
public class UserDaoImpl extends ServiceImpl<UserDOMapper, UserDO> implements UserDao {

    public void update(UserDO user) {
        user.setUpdateTime(new Date());
        this.baseMapper.updateById(user);
        RedisUtils.del(RedisKeyConstants.USER + user.getId());
    }

    public UserDO getUserByName(String userName){
        return this.baseMapper.selectOne(new LambdaQueryWrapper<UserDO>()
                .eq(UserDO::getUserName, userName));
    }
}

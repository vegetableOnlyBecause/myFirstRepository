package com.example.dao;

import com.example.condition.UserCondition;
import com.example.mapper.UserDOMapper;
import com.example.model.UserDO;
import com.example.model.UserDOExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @title: 用户查询类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 14:19
 * @description:
 */
@Repository
public class UserDao {

    @Resource
    private UserDOMapper userDOMapper;

    public void save(UserDO user){
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDOMapper.insertSelective(user);
    }

    public void del(int id){
        UserDOExample example = new UserDOExample();
        UserDOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        userDOMapper.deleteByExample(example);
    }

    public void update(UserDO user) {
        user.setUpdateTime(new Date());
        userDOMapper.updateByPrimaryKeySelective(user);
    }

    public UserDO getUserById(int id){
        UserDOExample example = new UserDOExample();
        UserDOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<UserDO> dos = userDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }

    public UserDO getUserByName(String userName){
        UserDOExample example = new UserDOExample();
        UserDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<UserDO> dos = userDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }

    public PageInfo<UserDO> listInfo(UserCondition condition){
        UserDOExample example = new UserDOExample();
        UserDOExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(condition.getSortField()
                + " " + condition.getSortType());
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<UserDO> dos = userDOMapper.selectByExample(example);
        return new PageInfo<>(Optional.ofNullable(dos).orElse(Collections.emptyList()));
    }
}

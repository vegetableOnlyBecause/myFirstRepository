package com.example.dao;

import com.example.condition.UserCondition;
import com.example.mapper.CommonUserDOMapper;
import com.example.model.CommonUserDO;
import com.example.model.CommonUserDOExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @title: 用户查询类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 14:19
 * @description:
 */
@Repository
public class CommonUserDao {

    @Resource
    private CommonUserDOMapper commonUserDOMapper;

    public void save(CommonUserDO userDO){
        commonUserDOMapper.insertSelective(userDO);
    }

    public void del(String userId){
        CommonUserDOExample example = new CommonUserDOExample();
        CommonUserDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        commonUserDOMapper.deleteByExample(example);
    }


    public CommonUserDO getUserById(String userId){
        CommonUserDOExample example = new CommonUserDOExample();
        CommonUserDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<CommonUserDO> dos = commonUserDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }

    public PageInfo<CommonUserDO> listInfo(UserCondition condition){
        CommonUserDOExample example = new CommonUserDOExample();
        CommonUserDOExample.Criteria criteria = example.createCriteria();
        if (null != condition.getNickName()){
            criteria.andNickNameEqualTo(condition.getNickName());
        }
        example.setOrderByClause(condition.getSortField()
                + " " + condition.getSortType());
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<CommonUserDO> dos = commonUserDOMapper.selectByExample(example);
        return new PageInfo<>(Optional.ofNullable(dos).orElse(Collections.emptyList()));
    }


}

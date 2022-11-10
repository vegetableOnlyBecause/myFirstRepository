package com.example.dao;

import com.example.mapper.CommonUserDOMapper;
import com.example.model.CommonUserDO;
import com.example.model.CommonUserDOExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @title: 用户查询类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 14:19
 * @description:
 */
@Repository
public class CommonUserDao {

    @Resource
    private CommonUserDOMapper mapper;

    public void save(CommonUserDO userDO){
        mapper.insertSelective(userDO);
    }

    public void del(String userId){
        CommonUserDOExample example = new CommonUserDOExample();
        CommonUserDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        mapper.deleteByExample(example);
    }


    public CommonUserDO getUserById(String userId){
        CommonUserDOExample example = new CommonUserDOExample();
        CommonUserDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<CommonUserDO> dos = mapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }

    public PageInfo<CommonUserDO> listUserByConditions(Map<String, Object> conditions, int page, int pageSize){
        CommonUserDOExample example = new CommonUserDOExample();
        CommonUserDOExample.Criteria criteria = example.createCriteria();
        if (MapUtils.isNotEmpty(conditions)){
            if (null != conditions.get("nickName")){
                criteria.andNickNameEqualTo(String.valueOf(conditions.get("nickName")));
            }
        }
        PageHelper.startPage(page, pageSize);
        List<CommonUserDO> dos = mapper.selectByExample(example);
        return new PageInfo<>(dos);
    }


}

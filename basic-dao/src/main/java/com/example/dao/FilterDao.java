package com.example.dao;

import com.example.mapper.FilterDOMapper;
import com.example.mapper.FilterRuleDOMapper;
import com.example.model.FilterDO;
import com.example.model.FilterDOExample;
import com.example.model.FilterRuleDO;
import com.example.model.FilterRuleDOExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: 过滤规则Dao
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 10:56
 * @description: 过滤规则数据库查询方法集合
 */
@Repository
public class FilterDao {
    @Resource
    private FilterDOMapper filterDOMapper;

    @Resource
    private FilterRuleDOMapper filterRuleDOMapper;

    public FilterDO getFilterById(String id) {
        FilterDOExample example = new FilterDOExample();
        FilterDOExample.Criteria criteria = example.createCriteria();
        criteria.andFilterIdEqualTo(id).andEnableEqualTo(1);;
        List<FilterDO> dos = filterDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }

    public FilterRuleDO getRuleById(String id) {
        FilterRuleDOExample example = new FilterRuleDOExample();
        FilterRuleDOExample.Criteria criteria = example.createCriteria();
        criteria.andRuleIdEqualTo(id).andEnableEqualTo(1);
        List<FilterRuleDO> dos = filterRuleDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }
}

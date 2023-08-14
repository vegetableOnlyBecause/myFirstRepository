package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.FilterRuleDao;
import com.example.mapper.FilterRuleDOMapper;
import com.example.model.FilterRuleDO;
import org.springframework.stereotype.Repository;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/9 15:36
 * @description:
 */
@Repository("ruleDao")
public class FilterRuleDaoImpl extends ServiceImpl<FilterRuleDOMapper, FilterRuleDO> implements FilterRuleDao {

    /**
     * 开启标志.
     */
    private static final Integer OPEN_FLAG = 1;

    public FilterRuleDO getById(String id) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<FilterRuleDO>()
                .eq(FilterRuleDO::getRuleId, id)
                .eq(FilterRuleDO::getEnable, OPEN_FLAG));
    }
}

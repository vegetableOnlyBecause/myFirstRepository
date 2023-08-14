package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.FilterDao;
import com.example.mapper.FilterDOMapper;
import com.example.model.FilterDO;
import org.springframework.stereotype.Repository;

/**
 * @title: 过滤规则Dao
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 10:56
 * @description: 过滤规则数据库查询方法集合
 */
@Repository
public class FilterDaoImpl extends ServiceImpl<FilterDOMapper, FilterDO> implements FilterDao {
    /**
     * 开启标志.
     */
    private static final Integer OPEN_FLAG = 1;

    @Override
    public FilterDO getById(String id) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<FilterDO>()
        .eq(FilterDO::getFilterId, id)
        .eq(FilterDO::getEnable, OPEN_FLAG));
    }
}

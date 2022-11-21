package com.example.dao;

import com.example.condition.GoodCondition;
import com.example.mapper.GoodDOMapper;
import com.example.model.GoodDO;
import com.example.model.GoodDOExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @title: 商品表信息数据库操作类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 16:50
 * @description:
 */
@Repository
public class GoodDao {

    @Resource
    private GoodDOMapper goodDOMapper;

    public GoodDO getById(int id) {
        GoodDOExample example = new GoodDOExample();
        GoodDOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<GoodDO> dos = goodDOMapper.selectByExample(example);
        return Optional.ofNullable(dos.get(0)).orElse(null);
    }

    public List<GoodDO> listByType(int typeId) {
        GoodDOExample example = new GoodDOExample();
        GoodDOExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(typeId);
        List<GoodDO> dos = goodDOMapper.selectByExample(example);
        return Optional.ofNullable(dos).orElse(Collections.emptyList());
    }

    public PageInfo<GoodDO> listInfo(GoodCondition condition) {
        GoodDOExample example = new GoodDOExample();
        GoodDOExample.Criteria criteria = example.createCriteria();
        if (null != condition.getTypeId()) {
            criteria.andTypeIdEqualTo(condition.getTypeId());
        }
        example.setOrderByClause(condition.getSortField()
                + " " + condition.getSortType());
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<GoodDO> dos = goodDOMapper.selectByExample(example);
        return new PageInfo<>(Optional.ofNullable(dos).orElse(Collections.emptyList()));
    }

    public void save(GoodDO good) {
        good.setCreateTime(new Date());
        good.setUpdateTime(new Date());
        goodDOMapper.insertSelective(good);
    }

    public void del(int id) {
        GoodDOExample example = new GoodDOExample();
        GoodDOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        goodDOMapper.deleteByExample(example);
    }

}

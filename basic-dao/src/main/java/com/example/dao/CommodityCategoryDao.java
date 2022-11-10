package com.example.dao;

import com.example.mapper.CommodityCategoryDOMapper;
import com.example.model.CommodityCategoryDO;
import com.example.model.CommodityCategoryDOExample;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @title: 商品类目数据库操作类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:05
 * @description:
 */
@Repository
public class CommodityCategoryDao {

    @Resource
    private CommodityCategoryDOMapper commodityCategoryDOMapper;

    public CommodityCategoryDO getById(String categoryId) {
        CommodityCategoryDOExample example = new CommodityCategoryDOExample();
        CommodityCategoryDOExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<CommodityCategoryDO> dos = commodityCategoryDOMapper.selectByExample(example);
        return Optional.ofNullable(dos.get(0)).orElse(null);
    }

    public CommodityCategoryDO getByParentId(String parentCategoryId) {
        CommodityCategoryDOExample example = new CommodityCategoryDOExample();
        CommodityCategoryDOExample.Criteria criteria = example.createCriteria();
        criteria.andParentCategoryIdEqualTo(parentCategoryId);
        List<CommodityCategoryDO> dos = commodityCategoryDOMapper.selectByExample(example);
        return Optional.ofNullable(dos.get(0)).orElse(null);
    }

    public List<CommodityCategoryDO> all() {
        CommodityCategoryDOExample example = new CommodityCategoryDOExample();
        List<CommodityCategoryDO> dos = commodityCategoryDOMapper.selectByExample(example);
        return Optional.ofNullable(dos).orElse(Collections.emptyList());
    }

    public void save(CommodityCategoryDO commodityCategoryDO) {
        commodityCategoryDOMapper.insertSelective(commodityCategoryDO);
    }

    public void delById(String categoryId) {
        CommodityCategoryDOExample example = new CommodityCategoryDOExample();
        CommodityCategoryDOExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        commodityCategoryDOMapper.deleteByExample(example);
    }

    public void delByParentId(String parentCategoryId) {
        CommodityCategoryDOExample example = new CommodityCategoryDOExample();
        CommodityCategoryDOExample.Criteria criteria = example.createCriteria();
        criteria.andParentCategoryIdEqualTo(parentCategoryId);
        commodityCategoryDOMapper.deleteByExample(example);
    }
}

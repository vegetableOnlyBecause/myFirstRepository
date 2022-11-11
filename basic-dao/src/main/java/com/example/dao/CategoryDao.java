package com.example.dao;

import com.example.condition.CategoryCondition;
import com.example.mapper.CategoryDOMapper;
import com.example.model.CategoryDO;
import com.example.model.CategoryDOExample;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @title: 商品类目数据库操作类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:05
 * @description:
 */
@Repository
public class CategoryDao {

    @Resource
    private CategoryDOMapper categoryDOMapper;

    public CategoryDO getById(String categoryId) {
        CategoryDOExample example = new CategoryDOExample();
        CategoryDOExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<CategoryDO> dos = categoryDOMapper.selectByExample(example);
        return Optional.ofNullable(dos.get(0)).orElse(null);
    }

    public CategoryDO getByParentId(String parentCategoryId) {
        CategoryDOExample example = new CategoryDOExample();
        CategoryDOExample.Criteria criteria = example.createCriteria();
        criteria.andParentCategoryIdEqualTo(parentCategoryId);
        List<CategoryDO> dos = categoryDOMapper.selectByExample(example);
        return Optional.ofNullable(dos.get(0)).orElse(null);
    }

    public List<CategoryDO> listInfo(CategoryCondition condition) {
        CategoryDOExample example = new CategoryDOExample();
        CategoryDOExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(condition.getCategoryName())) {
            criteria.andCategoryNameLike(condition.getCategoryName());
        }
        example.setOrderByClause(condition.getSortField()
                + " " + condition.getSortType());
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<CategoryDO> dos = categoryDOMapper.selectByExample(example);
        return Optional.ofNullable(dos).orElse(Collections.emptyList());
    }

    public String save(CategoryDO categoryDO) {
        if (StringUtils.isBlank(categoryDO.getCategoryId())) {
            categoryDO.setCategoryId(UUID.randomUUID().toString());
        }
        categoryDOMapper.insertSelective(categoryDO);
        return categoryDO.getCategoryId();
    }

    public void delById(String categoryId) {
        CategoryDOExample example = new CategoryDOExample();
        CategoryDOExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        categoryDOMapper.deleteByExample(example);
    }

    public void delByParentId(String parentCategoryId) {
        CategoryDOExample example = new CategoryDOExample();
        CategoryDOExample.Criteria criteria = example.createCriteria();
        criteria.andParentCategoryIdEqualTo(parentCategoryId);
        categoryDOMapper.deleteByExample(example);
    }
}

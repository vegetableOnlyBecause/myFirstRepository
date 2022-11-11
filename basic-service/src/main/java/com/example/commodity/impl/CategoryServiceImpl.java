package com.example.commodity.impl;

import com.example.commodity.CategoryService;
import com.example.commodity.dto.CategoryCreateDTO;
import com.example.commodity.dto.CategoryDTO;
import com.example.commodity.util.CategoryUtils;
import com.example.commodity.util.PageInfoUtils;
import com.example.condition.CategoryCondition;
import com.example.dao.CategoryDao;
import com.example.model.CategoryDO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @title: 类目服务类
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:01
 * @description:
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public String save(CategoryCreateDTO create) {
        return categoryDao.save(CategoryUtils.dto2do(create));
    }

    @Override
    public PageInfo<CategoryDTO> listInfo(CategoryCondition condition) {
        PageInfo<CategoryDO> result = categoryDao.listInfo(condition);
        return PageInfoUtils.pageInfoTrans(result, CategoryUtils::do2dto);
    }
}

package com.example.commodity;

import com.example.commodity.dto.CategoryCreateDTO;
import com.example.commodity.dto.CategoryDTO;
import com.example.condition.CategoryCondition;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @title: 商品类目操作
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:40
 * @description:
 */
public interface CategoryService {

    String save(CategoryCreateDTO create);

    PageInfo<CategoryDTO> listInfo(CategoryCondition condition);
}

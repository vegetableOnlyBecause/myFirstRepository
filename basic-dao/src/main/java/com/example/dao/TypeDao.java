package com.example.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.condition.TypeCondition;
import com.example.model.TypeDO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/14 11:32
 * @description:
 */
public interface TypeDao extends IService<TypeDO> {

    TypeDO getByName(String name);
    PageInfo<TypeDO> listInfo(TypeCondition condition);
    List<TypeDO> all();

}

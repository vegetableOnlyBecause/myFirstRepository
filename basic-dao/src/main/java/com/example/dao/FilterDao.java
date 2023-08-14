package com.example.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.FilterDO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/8 17:29
 * @description:
 */
public interface FilterDao extends IService<FilterDO> {

    FilterDO getById(String id);
}

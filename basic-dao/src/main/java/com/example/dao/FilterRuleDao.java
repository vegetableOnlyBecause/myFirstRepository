package com.example.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.FilterRuleDO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/9 15:34
 * @description:
 */
public interface FilterRuleDao extends IService<FilterRuleDO> {

    FilterRuleDO getById(String id);
}

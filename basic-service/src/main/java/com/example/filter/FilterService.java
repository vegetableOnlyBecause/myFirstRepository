package com.example.filter;

import com.example.filter.bo.FilterBO;

/**
 * @title: 规则引擎处理Service
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 11:01
 * @description: 规则引擎处理相关方法
 */
public interface FilterService {

    /**
     * 组合规则处理.
     * @param filterId 组合规则id
     * @param filterBO 规则处理所需BO
     * @return 组合规则处理结果
     */
    boolean filter(String filterId, FilterBO filterBO);
}

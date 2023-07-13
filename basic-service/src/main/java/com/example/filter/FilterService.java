package com.example.filter;

import com.example.filter.bo.FilterBO;

/**
 * @title: 规则引擎处理Service
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 11:01
 * @description: 规则引擎处理相关方法
 */
public interface FilterService {

    boolean filter(String filterId, FilterBO filterBO);
}

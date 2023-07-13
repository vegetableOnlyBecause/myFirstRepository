package com.example.filter;

import com.example.filter.bo.FilterBO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 11:38
 * @description:
 */
public interface QlExpressService {

    Object deal(String express, String type, FilterBO filterBO);
}

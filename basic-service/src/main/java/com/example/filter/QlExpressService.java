package com.example.filter;

import com.example.filter.bo.FilterBO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 11:38
 * @description:
 */
public interface QlExpressService {

    /**
     * QLExpression处理方法
     * @param express 表达式
     * @return 表达式处理结果
     */
    Object deal(String express);

    /**
     * QLExpression规则引擎处理方法.
     * @param express 表达式
     * @param type 业务类型(不需要可传null)
     * @param filterBO 过滤规则所需对象(不需要可传null)
     * @return 规则处理结果
     */
    Object deal(String express, String type, FilterBO filterBO);
}

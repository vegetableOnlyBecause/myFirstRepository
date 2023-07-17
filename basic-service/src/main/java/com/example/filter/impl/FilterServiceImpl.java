package com.example.filter.impl;

import com.example.dao.FilterDao;
import com.example.filter.FilterService;
import com.example.filter.QlExpressService;
import com.example.filter.bo.FilterBO;
import com.example.model.FilterDO;
import com.example.model.FilterRuleDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @title: 规则引擎处理实现类
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 11:02
 * @description:
 */
@Slf4j
@Service
public class FilterServiceImpl implements FilterService {

    /**
     * 规则Dao.
     */
    @Resource
    private FilterDao filterDao;
    /**
     * QLExpression处理Service.
     */
    @Resource
    private QlExpressService expressService;


    @Override
    public boolean filter(String filterId, FilterBO filterBO) {
        FilterDO filter = filterDao.getFilterById(filterId);
        return null == filter || deal(filter.getFilterContent(), filterBO);
    }


    /**
     * 组合规则处理方法.
     * @param content 组合规则数据库配置规则
     * @param filterBO 规则处理所需BO
     * @return 组合规则处理结果
     */
    private boolean deal(String content, FilterBO filterBO) {
        StringBuilder ruleId = new StringBuilder();
        // 处理单个rule后获得结果组成的表达式
        StringBuilder express = new StringBuilder();
        // 本地缓存, 单次规则结果Map
        Map<String, Object> ruleId2Result = new HashMap<>();
        int length = content.toCharArray().length;
        for (int i = 0; i < length; i++) {
            char character = content.toCharArray()[i];
            if (Character.isDigit(character)) {
                ruleId.append(character);
                if (i == length - 1) {
                    express.append(dealSingleRule(ruleId.toString(), filterBO, ruleId2Result));
                }
            } else {
                if (StringUtils.isNotBlank(ruleId)) {
                    express.append(dealSingleRule(ruleId.toString(), filterBO, ruleId2Result));
                    ruleId = new StringBuilder();
                }
                express.append(character);
            }
        }
        Object flag = expressService.deal(express.toString(), null, filterBO);
        return null != flag ? (Boolean)flag : false;
    }


    /**
     * 单个规则处理方法.
     * @param ruleId 规则Id
     * @param filterBO 过滤规则对象
     * @param ruleId2Result 本地缓存规则结果Map
     * @return 处理单个规则处理结果
     */
    private Object dealSingleRule(String ruleId, FilterBO filterBO,
                                  Map<String, Object> ruleId2Result) {
        Object result = ruleId2Result.get(ruleId);
        if (null == result) {
            FilterRuleDO ruleDO = filterDao.getRuleById(ruleId);
            result = null == ruleDO ? true :
                    expressService.deal(ruleDO.getRule(), ruleDO.getModelType(), filterBO);
            if (null == result) {
                log.error("规则处理失败, ruleId:{}", ruleId);
                return false;
            }
            ruleId2Result.put(ruleId, result);
        }
        return result;
    }
}

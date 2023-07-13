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
import java.util.*;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 11:02
 * @description:
 */
@Slf4j
@Service
public class FilterServiceImpl implements FilterService {
    @Resource
    private FilterDao filterDao;

    @Resource
    private QlExpressService expressService;

    @Override
    public boolean filter(String filterId, FilterBO filterBO) {
        FilterDO filter = filterDao.getFilterById(filterId);
        if (null == filter) {
            return true;
        }
        String content = filter.getFilterContent();
        Map<String, Object> ruleId2Result = new HashMap<>();
        List<String> ruleIds = queryRuleIds(content);
        for (String ruleId : ruleIds) {
            Object deal = ruleId2Result.get(ruleId);
            if (null == deal) {
                FilterRuleDO ruleDO = filterDao.getRuleById(ruleId);
                if (null == ruleDO) {
                    deal = true;
                } else {
                    deal = expressService.deal(ruleDO.getRule(),
                            ruleDO.getModelType(), filterBO);
                    if (null == deal) {
                        log.error(ruleDO.getRule());
                        return false;
                    }
                }
                ruleId2Result.put(ruleId, deal);
            }
            content = content.replaceFirst(ruleId, String.valueOf(deal));
        }
        Object flag = expressService.deal(content, "", filterBO);
        if (null != flag) {
            return (Boolean)flag;
        }
        return false;
    }


    public List<String> queryRuleIds(String content) {
        List<String> numList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < content.toCharArray().length; i++) {
            char c = content.toCharArray()[i];
            if (Character.isDigit(c)) {
                builder.append(c);
                if (i == content.toCharArray().length - 1) {
                    numList.add(new String(builder));
                }
            } else {
                String num = new String(builder);
                if (StringUtils.isNotBlank(num)) {
                    numList.add(num);
                    builder = new StringBuilder();
                }
            }
        }
        return numList;
    }
}

package com.example.filter.impl;

import com.example.filter.QlExpressService;
import com.example.filter.bo.FilterBO;
import com.example.filter.impl.opr.TimeJudgeFunc;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 11:03
 * @description:
 */
@Slf4j
@Service
public class QLExpressServiceImpl implements QlExpressService {
    /**
     * 时间判断方法.
     */
    @Resource
    private TimeJudgeFunc timeJudgeFunc;

    /**
     * QLExpression执行器.
     */
    private static final ExpressRunner runner = new ExpressRunner();


    @PostConstruct
    public void initRunner() {
        try {
            runner.addFunction("timeJudge", timeJudgeFunc);
        } catch (Exception e) {
            log.error("自定义运算符加载失败, exception:{}", e.getMessage());
        }
    }


    @Override
    public Object deal(String express) {
        return deal(express, null, null);
    }


    @Override
    public Object deal(String express, String type, FilterBO filterBO) {
        DefaultContext<String, Object> context = new DefaultContext<>();
        // 若没有业务类型则不进行上下文填充
        Optional.ofNullable(type).ifPresent(t -> initContext(type, filterBO, context));
        List<String> errorList = new ArrayList<>();
        try {
            return runner.execute(express, context, errorList, true, false);
        } catch (Exception e) {
            log.error("exception:{}", e.toString());
            log.error("QLExpress计算失败, express:{}, errorList:{}", express, errorList);
        }
        return null;
    }


    /**
     * 初始化QLExpression中上下文Map.
     * @param context 上下文Map
     * @param type 业务类型
     * @param filterBO 规则所需对象集合
     */
    private void initContext(String type, FilterBO filterBO,
                             DefaultContext<String, Object> context) {
        switch (type) {
            case "user":
                context.put(type, filterBO.getUserDTO());
                break;
            case "good":
                context.put(type, filterBO.getGoodDTO());
                break;
            default:
                log.error("没有该类型的规则引擎, type:{}", type);
                break;
        }
    }
}

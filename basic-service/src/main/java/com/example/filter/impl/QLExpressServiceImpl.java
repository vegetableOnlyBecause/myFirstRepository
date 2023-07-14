package com.example.filter.impl;

import com.example.filter.QlExpressService;
import com.example.filter.bo.FilterBO;
import com.example.filter.opr.JoinOperator;
import com.example.user.dto.UserDTO;
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
    @Resource
    private JoinOperator joinOperator;

    private static final ExpressRunner runner = new ExpressRunner();

    @PostConstruct
    public void initRunner() {
        try {
            runner.addOperator("join", joinOperator);
        } catch (Exception e) {
            log.error("自定义运算符加载失败, exception:{}", e.getMessage());
        }
    }

    @Override
    public Object deal(String express, String type, FilterBO filterBO) {
        DefaultContext<String, Object> context = new DefaultContext<>();
        Optional.ofNullable(type)
                .ifPresent(t -> initContext(context, type, filterBO));
        List<String> errorList = new ArrayList<>();
        try {
            return runner.execute(express, context, errorList, true, false);
        } catch (Exception e) {
            log.error("规则引擎计算失败, express:{}, exception:{}", express, e.getMessage());
        }
        return null;
    }


    private void initContext(DefaultContext<String, Object> context,
                             String type, FilterBO filterBO) {
        switch (type) {
            case "user":
                context.put("user", filterBO.getUserDTO());
                break;
            case "good":
                context.put("good", filterBO.getGoodDTO());
                break;
            default:
                log.error("没有该类型的规则引擎, type:{}", type);
                break;
        }
    }


    public static void main(String[] args) throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        UserDTO dto = new UserDTO();
        dto.setCredit(5F);
        context.put("user",dto);
        String express = "user.credit>5";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }
}

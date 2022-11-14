package com.example.controller.order;

import com.alibaba.fastjson.JSON;
import com.example.commodity.util.PageInfoUtils;
import com.example.condition.OrderCondition;
import com.example.controller.order.util.OrderTransUtils;
import com.example.order.OrderService;
import com.example.order.dto.OrderDTO;
import com.example.response.OperationResult;
import com.example.user.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:38
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public OperationResult<Object> listInfo(@RequestParam Map<String, String> param) {
        OrderCondition condition = JSON.parseObject(JSON.toJSONString(param), OrderCondition.class);
        if (null == condition) {
            condition = new OrderCondition();
        }
        PageInfo<OrderDTO> dtos = orderService.listInfo(condition);
        List<OrderDTO> list = dtos.getList();
        list.forEach(dto -> {
            dto.setBuyer(userService.getUserById(dto.getBuyer().getUserId()));
            dto.setSeller(userService.getUserById(dto.getSeller().getUserId()));
        });
        return OperationResult.succ(
                PageInfoUtils.pageInfoTrans(dtos, OrderTransUtils::dto2vo));
    }
}

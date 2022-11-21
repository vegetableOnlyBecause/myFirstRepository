package com.example.controller.order;

import com.alibaba.fastjson.JSON;
import com.example.good.util.PageInfoUtils;
import com.example.condition.OrderCondition;
import com.example.controller.order.util.OrderTransUtils;
import com.example.controller.order.vo.OrderCreate;
import com.example.order.OrderService;
import com.example.order.dto.OrderDTO;
import com.example.response.OperationResult;
import com.example.user.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public OperationResult<Object> create(@Validated @RequestBody OrderCreate create) {
        return OperationResult.succ(orderService.save(OrderTransUtils.vo2dto(create)));
    }

    @RequestMapping(value="/cancel/{id}", method = RequestMethod.PUT)
    public OperationResult cancel(@PathVariable String id) {
        orderService.cancel(id);
        return OperationResult.succ();
    }

    @RequestMapping(value="/deliver/{id}", method = RequestMethod.PUT)
    public OperationResult deliver(@PathVariable String id) {
        orderService.deliver(id);
        return OperationResult.succ();
    }

    @RequestMapping(value="/pay/{id}", method = RequestMethod.PUT)
    public OperationResult pay(@PathVariable String id) {
        orderService.deliver(id);
        return OperationResult.succ();
    }

    @RequestMapping(method = RequestMethod.GET)
    public OperationResult<Object> listInfo(@RequestParam Map<String, String> param) {
        OrderCondition condition = JSON.parseObject(JSON.toJSONString(param), OrderCondition.class);
        if (null == condition) {
            condition = new OrderCondition();
        }
        PageInfo<OrderDTO> dtos = orderService.listInfo(condition);
        List<OrderDTO> list = dtos.getList();
        list.forEach(dto -> {
            dto.setBuyer(userService.getUserById(dto.getBuyer().getId()));
            dto.setSeller(userService.getUserById(dto.getSeller().getId()));
        });
        return OperationResult.succ(
                PageInfoUtils.pageInfoTrans(dtos, OrderTransUtils::dto2vo));
    }
}

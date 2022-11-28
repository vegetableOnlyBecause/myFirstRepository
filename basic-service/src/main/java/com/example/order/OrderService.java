package com.example.order;

import com.example.condition.OrderCondition;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.github.pagehelper.PageInfo;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:32
 * @description:
 */
public interface OrderService {

    Integer save(OrderCreateDTO dto);

    PageInfo<OrderDTO> listInfo(OrderCondition condition);
}

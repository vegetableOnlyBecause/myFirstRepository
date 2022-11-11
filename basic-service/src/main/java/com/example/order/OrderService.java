package com.example.order;

import com.example.condition.OrderCondition;
import com.example.order.dto.OrderDTO;
import com.github.pagehelper.PageInfo;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:32
 * @description:
 */
public interface OrderService {

    PageInfo<OrderDTO> listInfo(OrderCondition condition);
}

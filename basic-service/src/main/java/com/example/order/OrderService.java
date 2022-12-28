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

    void save(OrderCreateDTO dto) throws Exception;

    PageInfo<OrderDTO> listInfo(OrderCondition condition);

    void del(Integer id) throws Exception;
}

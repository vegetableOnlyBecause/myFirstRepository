package com.example.condition;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @title: 订单查询条件
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:18
 * @description:
 */
@Data
public class OrderCondition extends LocalPageInfo{

    private Integer orderId;

    private Integer buyerId;

    private Integer salerId;

    private List<Integer> orderStatus;

    private Date startTime;

    private Date endTime;
}

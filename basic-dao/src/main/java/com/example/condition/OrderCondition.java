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

    private String orderId;

    private String buyerUserId;

    private String sellerUserId;

    private List<String> orderStatus;

    private List<String> orderType;

    private Date startTime;

    private Date endTime;
}

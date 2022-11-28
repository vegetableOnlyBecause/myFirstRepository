package com.example.controller.order.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: 订单创建信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/14 10:34
 * @description:
 */
@Data
public class OrderCreate implements Serializable {

    private static final long serialVersionUID = -2952314692695919661L;

    private Integer salerId;

    private Integer buyerId;

    private Integer goodsId;
}

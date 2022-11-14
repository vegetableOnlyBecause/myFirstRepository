package com.example.order.dto;

import com.example.user.dto.UserDTO;
import lombok.Data;

import java.util.Date;

/**
 * @title: OrderDTO
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:33
 * @description:
 */
@Data
public class OrderDTO {

    private String orderId;

    private String goodsId;

    private UserDTO buyer;

    private UserDTO seller;

    private Integer goodsNum;

    private Long totalAmount;

    private Long payAmount;

    private String orderStatus;

    private String orderType;

    private Date payTime;

    private Date createTime;

    private Date updateTime;
}

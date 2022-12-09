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

    private Integer id;

    private Integer goodsId;

    private String name;

    private Float price;

    private Integer status;

    private Date createTime;
}

package com.example.good.dto;

import lombok.Data;

import java.util.Date;

/**
 * @title: 商品DTO
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:44
 * @description:
 */
@Data
public class GoodDTO {

    private Integer id;

    private String name;

    private Integer typeId;

    private Integer userId;

    private Float price;

    private String imgUrl;

    private Float realPrice;

    private Date polishTime;

    private Date endTime;

    private Integer commentNum;

    private Integer status;

    private String description;

    private Date createTime;

    private Date updateTime;
}

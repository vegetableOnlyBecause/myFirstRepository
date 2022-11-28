package com.example.good.dto;

import lombok.Data;

import java.util.Date;

/**
 * @title: 商品创建信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 18:47
 * @description:
 */
@Data
public class GoodOprDTO {

    private Integer id;

    private Integer userId;

    private Integer typeId;

    private String name; //商品名

    private Float price; //价格

    private Float realPrice; //原价

    private Date endTime;

    private Date polishTime;

    private Integer commentNum;

    private String description;

    private String imgUrl;

    private Integer status = 1; //上架 or 下架
}

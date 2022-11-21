package com.example.controller.good.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 商品创建信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 18:58
 * @description:
 */
@Data
public class GoodCreate implements Serializable {

    private static final long serialVersionUID = -5608164219658996521L;

    private Integer id;

    private Integer userId;

    private Integer typeId;

    private String name; //商品名

    private float price; //价格

    private float realPrice; //原价

    private Date endTime;

    private String polishTime;

    private Integer commentNum;

    private String description;

    private String imgUrl;

    private Integer status = 1;//上架 or 下架
}

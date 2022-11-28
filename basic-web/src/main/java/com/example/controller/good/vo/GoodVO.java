package com.example.controller.good.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 商品展示信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 19:34
 * @description:
 */
@Data
public class GoodVO implements Serializable {
    private static final long serialVersionUID = -5284956025125671579L;

    private Integer id;

    private String name;

    private Integer typeId;

    private Integer userId;

    private Float price;

    private String imgUrl;

    private Float realPrice;

//    private Date polishTime;

    private String endTime;

    private Integer commentNum;

    private Integer status;

    private String description;

    private String createTime;

    private String updateTime;
}

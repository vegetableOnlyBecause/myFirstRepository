package com.example.good.dto;

import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:55
 * @description:
 */
@Data
public class TypeCreateDTO {

    private String parentCategoryId;

    private String categoryName;

    private String detail;

    private Date createTime = new Date();

    private Date updateTime = new Date();
}

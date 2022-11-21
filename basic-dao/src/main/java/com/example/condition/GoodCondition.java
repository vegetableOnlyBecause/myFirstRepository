package com.example.condition;

import lombok.Data;

/**
 * @title: 商品查询信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 14:16
 * @description:
 */
@Data
public class GoodCondition extends LocalPageInfo {

    private String goodName;

    private Integer typeId;

}

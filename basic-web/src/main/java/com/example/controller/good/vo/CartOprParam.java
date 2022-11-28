package com.example.controller.good.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/23 11:31
 * @description:
 */
@Data
public class CartOprParam {

    private Integer userId;

    private Integer goodsId;
}

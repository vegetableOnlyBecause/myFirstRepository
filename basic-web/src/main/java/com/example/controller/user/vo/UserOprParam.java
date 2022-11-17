package com.example.controller.user.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @title: 用户创建所需信息
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 16:43
 * @description:
 */
@Data
public class UserOprParam implements Serializable {
    private static final long serialVersionUID = 1046459142254988930L;

    private Integer id;

    private String username;

    private String password;

    private String phone;

    private Integer power;

    private String qq;

    private Float coin;

    private Integer goodsNum;

    private Float credit;
}

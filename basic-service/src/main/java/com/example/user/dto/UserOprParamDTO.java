package com.example.user.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 创建用户所需信息类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:43
 * @description:
 */
@Data
public class UserOprParamDTO implements Serializable {

    private static final long serialVersionUID = 229156637375701790L;

    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private Integer power;

    private String qq;

    private Float coin;

    private Integer goodsNum;

    private Float credit;
}

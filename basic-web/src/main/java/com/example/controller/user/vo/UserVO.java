package com.example.controller.user.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @title: 用户信息VO
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:58
 * @description:
 */
@Data
public class UserVO implements Serializable {

    private Integer id;

    private String username;

    private String phone;

    private String qq;

    private Date createTime;

    private Integer goodsNum = 0;

    private Integer power;

    private float coin;

    private float credit = 5;
}

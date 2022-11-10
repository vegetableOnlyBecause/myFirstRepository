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
public class UserCreate implements Serializable {
    private static final long serialVersionUID = 1046459142254988930L;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    private String phoneNumber;

    private Date birthday;

    private String email;
}

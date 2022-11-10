package com.example.controller.user.vo;

import lombok.Data;

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
    private String userId;

    private String userName;

    private String nickName;

    private String phoneNumber;

    private Date birthday;

    private String email;

    private Date registerTime;
}

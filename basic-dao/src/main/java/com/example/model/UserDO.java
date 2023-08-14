package com.example.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("common_user")
public class UserDO {
    @TableId
    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private String qq;

    private Integer goodsNum;

    private Integer power;

    private Float coin;

    private Float credit;

    private Date createTime;

    private Date updateTime;
}
package com.example.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comments")
public class CommentDO {
    @TableId
    private Integer id;

    private Integer goodsId;

    private Integer userId;

    private String userName;

    private String comments;

    private Date createTime;

}
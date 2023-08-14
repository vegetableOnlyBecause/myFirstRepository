package com.example.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("goods")
public class GoodDO {
    @TableId
    private Integer id;

    private String name;

    private Integer typeId;

    private Integer userId;

    private Float price;

    private Integer num;

    private String imgUrl;

    private Float realPrice;

    private Date polishTime;

    private Date endTime;

    private String description;

    private Integer commentNum;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
package com.example.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("filter")
public class FilterDO {
    @TableId
    private Integer id;

    private String filterId;

    private String filterName;

    private String content;

    private Integer enable;

    private Date createTime;
}
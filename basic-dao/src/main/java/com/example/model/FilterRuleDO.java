package com.example.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("filter_rule")
public class FilterRuleDO {
    @TableId
    private Integer id;

    private String ruleId;

    private String ruleName;

    private String modelType;

    private String content;

    private Integer enable;

    private Date createTime;
}
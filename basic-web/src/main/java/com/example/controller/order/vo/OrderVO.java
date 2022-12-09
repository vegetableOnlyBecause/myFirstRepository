package com.example.controller.order.vo;

import com.example.controller.user.vo.UserVO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:40
 * @description:
 */
@Data
public class OrderVO implements Serializable {
    private static final long serialVersionUID = 6017659074978481810L;

    private Integer id;

    private String name;

    private Float price;

    private Date createTime;

}

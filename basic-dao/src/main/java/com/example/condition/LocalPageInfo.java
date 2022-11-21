package com.example.condition;

import lombok.Data;

/**
 * @title: 分页信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:09
 * @description:
 */
@Data
public class LocalPageInfo {

    private Integer page;

    private Integer pageSize;

    private String sortField = "create_time";

    private String sortType = "desc";

    public void initPageInfo(){
        if (null == pageSize || pageSize < 0) {
            pageSize = 999;
        }
        if (null == page || page < 0) {
            page = 1;
        }
    }
}

package com.example.good;

import com.example.good.dto.GoodCreateDTO;
import com.example.good.dto.GoodDTO;
import com.example.condition.GoodCondition;
import com.github.pagehelper.PageInfo;

/**
 * @title: 商品操作
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:39
 * @description:
 */
public interface GoodService {
    GoodDTO getById(Integer id);

    PageInfo<GoodDTO> listInfo(GoodCondition condition);

    Integer save(GoodCreateDTO dto);
}

package com.example.good;

import com.example.good.dto.GoodOprDTO;
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

    void delById(Integer id);

    PageInfo<GoodDTO> listInfo(GoodCondition condition);

    Integer save(GoodOprDTO dto) throws Exception;

    void update(GoodOprDTO dto);

    void lessInventory(Integer goodId, Integer num) throws Exception;
}

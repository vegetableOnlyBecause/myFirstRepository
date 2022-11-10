package com.example.commodity;

import com.example.commodity.dto.CommodityCreateDTO;
import com.example.commodity.dto.CommodityDTO;

import java.util.List;

/**
 * @title: 商品操作
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:39
 * @description:
 */
public interface CommodityService {

    List<CommodityDTO> listByCategoryId(String categoryId);

    String save(CommodityCreateDTO dto);
}

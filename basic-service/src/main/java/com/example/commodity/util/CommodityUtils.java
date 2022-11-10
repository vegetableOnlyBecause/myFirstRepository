package com.example.commodity.util;

import com.example.commodity.dto.CommodityCreateDTO;
import com.example.commodity.dto.CommodityDTO;
import com.example.model.CommodityDO;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @title: 商品相关工具类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:48
 * @description:
 */
public class CommodityUtils {

    public static CommodityDTO do2dto(CommodityDO commodityDO) {
        if (null == commodityDO) {
            return null;
        }
        CommodityDTO dto = new CommodityDTO();
        BeanUtils.copyProperties(commodityDO, dto);
        return dto;
    }

    public static List<CommodityDTO> dos2dtos(List<CommodityDO> dos) {
        if (CollectionUtils.isEmpty(dos)) {
            return Collections.emptyList();
        }
        List<CommodityDTO> dtos = new ArrayList<>();
        for (CommodityDO commodityDO : dos) {
            CommodityDTO dto = do2dto(commodityDO);
            if (null != dto) {
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public static CommodityDO dto2do(CommodityCreateDTO dto) {
        if (null == dto) {
            return null;
        }
        CommodityDO commodityDO = new CommodityDO();
        BeanUtils.copyProperties(dto, commodityDO);
        commodityDO.setCommodityId(UUID.randomUUID().toString());
        return commodityDO;
    }
}

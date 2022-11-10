package com.example.controller.commodity.util;

import com.example.commodity.dto.CommodityCreateDTO;
import com.example.commodity.dto.CommodityDTO;
import com.example.controller.commodity.vo.CommodityCreate;
import com.example.controller.commodity.vo.CommodityVO;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title: 商品vo转换类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 19:09
 * @description:
 */
public class CommodityTransUtils {

    public static CommodityCreateDTO vo2dto(CommodityCreate create) {
        if (null == create) {
            return null;
        }
        CommodityCreateDTO dto = new CommodityCreateDTO();
        BeanUtils.copyProperties(create, dto);
        return dto;
    }

    public static CommodityVO dto2vo(CommodityDTO dto) {
        if (null == dto) {
            return null;
        }
        CommodityVO vo = new CommodityVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static List<CommodityVO> dtos2vos(List<CommodityDTO> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        List<CommodityVO> vos = new ArrayList<>();
        for (CommodityDTO dto : dtos) {
            CommodityVO vo = dto2vo(dto);
            if (null != vo) {
                vos.add(vo);
            }
        }
        return vos;
    }
}

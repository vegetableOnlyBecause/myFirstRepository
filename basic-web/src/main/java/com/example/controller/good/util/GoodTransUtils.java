package com.example.controller.good.util;

import com.example.good.dto.GoodCreateDTO;
import com.example.good.dto.GoodDTO;
import com.example.controller.good.vo.GoodCreate;
import com.example.controller.good.vo.GoodVO;
import org.springframework.beans.BeanUtils;

/**
 * @title: 商品vo转换类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 19:09
 * @description:
 */
public class GoodTransUtils {

    public static GoodCreateDTO vo2dto(GoodCreate create) {
        if (null == create) {
            return null;
        }
        GoodCreateDTO dto = new GoodCreateDTO();
        BeanUtils.copyProperties(create, dto);
//        dto.setId(Integer.parseInt(create.getId()));
        return dto;
    }

    public static GoodVO dto2vo(GoodDTO dto) {
        if (null == dto) {
            return null;
        }
        GoodVO vo = new GoodVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }
}

package com.example.controller.good.util;

import com.example.good.dto.TypeCreateDTO;
import com.example.good.dto.TypeDTO;
import com.example.controller.good.vo.TypeCreate;
import com.example.controller.good.vo.TypeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:34
 * @description:
 */
public class TypeTransUtils {

    public static TypeVO dto2vo(TypeDTO dto) {
        if (null == dto) {
            return null;
        }
        TypeVO vo = new TypeVO();
        BeanUtils.copyProperties(dto, vo);
        vo.setId(""+dto.getId());
        return vo;
    }

    public static List<TypeVO> dtos2vos(List<TypeDTO> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        List<TypeVO> vos = new ArrayList<>();
        for (TypeDTO dto : dtos) {
            TypeVO vo = dto2vo(dto);
            if (null != vo) {
                vos.add(vo);
            }
        }
        return vos;
    }

    public static TypeCreateDTO vo2dto(TypeCreate create) {
        if (null == create) {
            return null;
        }
        TypeCreateDTO dto = new TypeCreateDTO();
        BeanUtils.copyProperties(create, dto);
        return dto;
    }
}

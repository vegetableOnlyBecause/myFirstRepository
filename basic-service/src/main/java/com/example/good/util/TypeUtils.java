package com.example.good.util;

import com.example.good.dto.TypeCreateDTO;
import com.example.good.dto.TypeDTO;
import com.example.model.TypeDO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:22
 * @description:
 */
public class TypeUtils {

    public static TypeDTO do2dto(TypeDO type) {
        if (null == type) {
            return null;
        }
        TypeDTO dto = new TypeDTO();
        BeanUtils.copyProperties(type, dto);
        return dto;
    }

    public static List<TypeDTO> dos2dtos(List<TypeDO> dos) {
        if (CollectionUtils.isEmpty(dos)) {
            return Collections.emptyList();
        }
        List<TypeDTO> dtos = new ArrayList<>();
        for (TypeDO type : dos) {
            TypeDTO dto = do2dto(type);
            if (null != dto) {
                dtos.add(dto);
            }
        }
        return dtos;
    }



    public static TypeDO dto2do(TypeCreateDTO dto) {
        if (null == dto) {
            return null;
        }
        TypeDO type = new TypeDO();
        BeanUtils.copyProperties(dto, type);
        return type;
    }
}

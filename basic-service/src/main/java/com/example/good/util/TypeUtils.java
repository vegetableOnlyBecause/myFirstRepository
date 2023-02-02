package com.example.good.util;

import com.example.common.utils.OprUtils;
import com.example.good.dto.TypeCreateDTO;
import com.example.good.dto.TypeDTO;
import com.example.model.TypeDO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:22
 * @description:
 */
public class TypeUtils {

    public static TypeDTO do2dto(TypeDO type) {
        return OprUtils.copyModel2Model(type, new TypeDTO());
    }

    public static TypeDO dto2do(TypeCreateDTO dto) {
        return OprUtils.copyModel2Model(dto, new TypeDO());
    }
}

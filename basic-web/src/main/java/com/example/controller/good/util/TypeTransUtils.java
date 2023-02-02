package com.example.controller.good.util;

import com.example.common.utils.OprUtils;
import com.example.controller.good.vo.TypeCreate;
import com.example.controller.good.vo.TypeVO;
import com.example.good.dto.TypeCreateDTO;
import com.example.good.dto.TypeDTO;

import java.util.function.Consumer;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:34
 * @description:
 */
public class TypeTransUtils {

    public static TypeVO dto2vo(TypeDTO dto) {
        Consumer<TypeVO> consumer = vo -> vo.setId("" + dto.getId());
        return OprUtils.model2Model(dto, new TypeVO(), consumer);
    }

    public static TypeCreateDTO vo2dto(TypeCreate create) {
        return OprUtils.copyModel2Model(create, new TypeCreateDTO());
    }
}

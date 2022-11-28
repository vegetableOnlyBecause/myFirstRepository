package com.example.good.util;

import com.example.good.dto.CommentCreateDTO;
import com.example.good.dto.CommentDTO;
import com.example.model.CommentDO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 13:56
 * @description:
 */
public class CommentUtils {

    public static CommentDTO do2dto(CommentDO comment) {
        if (null == comment) {
            return null;
        }
        CommentDTO dto = new CommentDTO();
        BeanUtils.copyProperties(comment, dto);
        return dto;
    }

    public static CommentDO dto2do(CommentCreateDTO dto) {
        if (null == dto) {
            return null;
        }
        CommentDO comment = new CommentDO();
        BeanUtils.copyProperties(dto, comment);
        return comment;
    }

    public static List<CommentDTO> dos2dtos(List<CommentDO> dos) {
        if (CollectionUtils.isEmpty(dos)) {
            return Collections.emptyList();
        }
        List<CommentDTO> dtos = new ArrayList<>();
        for (CommentDO comment : dos) {
            CommentDTO dto = do2dto(comment);
            if (null != dto) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}

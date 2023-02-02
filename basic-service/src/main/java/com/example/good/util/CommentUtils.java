package com.example.good.util;

import com.example.common.utils.OprUtils;
import com.example.good.dto.CommentCreateDTO;
import com.example.good.dto.CommentDTO;
import com.example.model.CommentDO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 13:56
 * @description:
 */
public class CommentUtils {

    public static CommentDTO do2dto(CommentDO comment){
        return OprUtils.copyModel2Model(comment, new CommentDTO());
    }

    public static CommentDO dto2do(CommentCreateDTO dto) {
        return OprUtils.copyModel2Model(dto, new CommentDO());
    }
}

package com.example.controller.good.util;

import com.example.common.utils.OprUtils;
import com.example.controller.good.vo.CommentCreate;
import com.example.controller.good.vo.CommentVO;
import com.example.good.dto.CommentCreateDTO;
import com.example.good.dto.CommentDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 14:50
 * @description:
 */
public class CommentTransUtils {

    public static CommentVO dto2vo(CommentDTO dto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Consumer<CommentVO> consumer = vo -> vo.setCreateTime(format.format(dto.getCreateTime()));
        return OprUtils.model2Model(dto, new CommentVO(), consumer);
    }

    public static CommentCreateDTO vo2dto(CommentCreate create) {
        return OprUtils.copyModel2Model(create, CommentCreateDTO.builder().build());
    }

    public static String date2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return  format.format(date);
    }
}

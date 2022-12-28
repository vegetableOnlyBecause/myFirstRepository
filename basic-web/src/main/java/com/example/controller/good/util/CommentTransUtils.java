package com.example.controller.good.util;

import com.example.controller.good.vo.CommentCreate;
import com.example.controller.good.vo.CommentVO;
import com.example.good.dto.CommentCreateDTO;
import com.example.good.dto.CommentDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;

import javax.print.DocFlavor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 14:50
 * @description:
 */
public class CommentTransUtils {

    public static CommentVO dto2vo(CommentDTO dto) {
        if (null == dto) {
            return null;
        }
        CommentVO vo = new CommentVO();
        BeanUtils.copyProperties(dto, vo);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        vo.setCreateTime(format.format(dto.getCreateTime()));
        return vo;
    }

    public static CommentCreateDTO vo2dto(CommentCreate create) {
        if (null == create) {
            return null;
        }
        CommentCreateDTO dto = new CommentCreateDTO();
        BeanUtils.copyProperties(create, dto);
        return dto;
    }

    public static String date2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return  format.format(date);
    }
}

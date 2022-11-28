package com.example.good;

import com.example.good.dto.CommentCreateDTO;
import com.example.good.dto.CommentDTO;

import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 13:12
 * @description:
 */
public interface CommentService {

    List<CommentDTO> listByGoodId(Integer goodId);

    void save(CommentCreateDTO create);
}

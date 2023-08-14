package com.example.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.CommentDO;

import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/8 17:20
 * @description:
 */
public interface CommentDao extends IService<CommentDO> {

    List<CommentDO> listByGoodId(Integer goodId);
}

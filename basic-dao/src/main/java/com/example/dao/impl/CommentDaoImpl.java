package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CommentDao;
import com.example.mapper.CommentDOMapper;
import com.example.model.CommentDO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @title: 评论查询
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 12:38
 * @description:
 */
@Repository
public class CommentDaoImpl extends ServiceImpl<CommentDOMapper, CommentDO> implements CommentDao {

    @Override
    public List<CommentDO> listByGoodId(Integer goodId) {
        List<CommentDO> dos = this.baseMapper.selectList(
                new LambdaQueryWrapper<CommentDO>().eq(CommentDO::getGoodsId, goodId));
        return CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList();
    }
}

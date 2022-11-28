package com.example.dao;

import com.example.mapper.CommentDOMapper;
import com.example.model.CommentDO;
import com.example.model.CommentDOExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @title: 评论查询
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 12:38
 * @description:
 */
@Repository
public class CommentDao {

    @Resource
    private CommentDOMapper commentDOMapper;

    public List<CommentDO> listByGoodId(Integer goodId) {
        CommentDOExample example = new CommentDOExample();
        CommentDOExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodId);
        List<CommentDO> dos = commentDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList();
    }

    public void save(CommentDO comment) {
        commentDOMapper.insertSelective(comment);
    }
}

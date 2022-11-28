package com.example.good.impl;

import com.example.dao.CommentDao;
import com.example.good.CommentService;
import com.example.good.dto.CommentCreateDTO;
import com.example.good.dto.CommentDTO;
import com.example.good.util.CommentUtils;
import com.example.model.CommentDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 13:12
 * @description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public List<CommentDTO> listByGoodId(Integer goodId) {
        List<CommentDO> dos = commentDao.listByGoodId(goodId);
        return CommentUtils.dos2dtos(dos);
    }

    @Override
    public void save(CommentCreateDTO create) {
        commentDao.save(CommentUtils.dto2do(create));
    }


}

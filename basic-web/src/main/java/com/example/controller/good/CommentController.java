package com.example.controller.good;

import com.example.common.utils.OprUtils;
import com.example.controller.good.util.CommentTransUtils;
import com.example.controller.good.vo.CommentVO;
import com.example.good.CommentService;
import com.example.good.dto.CommentCreateDTO;
import com.example.good.dto.CommentDTO;
import com.example.response.Result;
import com.example.response.ResultEnum;
import com.example.response.ResultUtil;
import com.example.user.UserService;
import com.example.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 12:28
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/market/comments")
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;

    @GetMapping(value = "/{goodsId}")
    public Result<List<CommentVO>> getGoodsComments(@PathVariable("goodsId") Integer goodsId){
        List<CommentDTO> dtos = commentService.listByGoodId(goodsId);
        return ResultUtil.success(ResultEnum.GET_GOODS_COMMENTS_LIST,
                OprUtils.models2Models(dtos, CommentTransUtils::dto2vo));
    }

    @PostMapping(value = "/add/{goodsId}/{userId}/{comment}")
    public Result addGoodsComments(@PathVariable("goodsId") Integer goodsId,
                                   @PathVariable("userId") Integer userId,
                                   @PathVariable("comment") String comment){
        CommentCreateDTO comments = new CommentCreateDTO();
        comments.setGoodsId(goodsId);
        comments.setUserId(userId);
        comments.setComments(comment);
        UserDTO user = userService.getUserById(userId);
        comments.setUserName(user.getUserName());
        commentService.save(comments);
        return ResultUtil.success(ResultEnum.ADD_GOODS_COMMENT);
    }
}


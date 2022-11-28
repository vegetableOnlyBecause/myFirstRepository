package com.example.controller.good;

import com.example.controller.good.util.GoodTransUtils;
import com.example.controller.good.vo.CartOprParam;
import com.example.controller.good.vo.GoodVO;
import com.example.good.CartService;
import com.example.good.GoodService;
import com.example.good.dto.CartDTO;
import com.example.good.dto.CartOprParamDTO;
import com.example.response.Result;
import com.example.response.ResultEnum;
import com.example.response.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/23 10:39
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/market")
public class CartController {

    @Resource
    private CartService cartService;
    @Resource
    private GoodService goodService;

    @GetMapping(value = "/cart-list/{userId}")
    public Result<List<GoodVO>> getOnesCartList(@PathVariable("userId") Integer userId){
        List<CartDTO> carts = cartService.listByUserId(userId);
        List<GoodVO> goods = new ArrayList<>();
        for (CartDTO cart : carts) {
            goods.add(GoodTransUtils.dto2vo(goodService.getById(cart.getGoodsId())));
        }
        return ResultUtil.success(ResultEnum.GET_ONES_CART,goods);
    }

    @PostMapping(value = "/cart/add/{userId}/{goodsId}")
    public Result addCart(@PathVariable("userId") Integer userId,
                          @PathVariable("goodsId") Integer goodsId){
        CartOprParamDTO param = new CartOprParamDTO();
        param.setUserId(userId);
        param.setGoodsId(goodsId);
        cartService.save(param);
        return ResultUtil.success(ResultEnum.ADD_CART);
    }

    @Transactional
    @PostMapping(value = "/cart/delete/{userId}/{goodsId}")
    public Result deleteCart(@PathVariable("userId") Integer userId,
                             @PathVariable("goodsId") Integer goodsId){
        cartService.del(userId,goodsId);
        return ResultUtil.success(ResultEnum.DROP_CART);
    }
}

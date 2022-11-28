package com.example.controller.order;

import com.alibaba.fastjson.JSON;
import com.example.controller.good.util.GoodTransUtils;
import com.example.controller.good.vo.GoodVO;
import com.example.good.GoodService;
import com.example.good.util.PageInfoUtils;
import com.example.condition.OrderCondition;
import com.example.controller.order.util.OrderTransUtils;
import com.example.controller.order.vo.OrderCreate;
import com.example.order.OrderService;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.response.OperationResult;
import com.example.response.Result;
import com.example.response.ResultEnum;
import com.example.response.ResultUtil;
import com.example.user.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:38
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/market/trade")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;
    @Resource
    private GoodService goodService;

    @RequestMapping(method = RequestMethod.GET)
    public OperationResult<Object> listInfo(@RequestParam Map<String, String> param) {
        OrderCondition condition = JSON.parseObject(JSON.toJSONString(param), OrderCondition.class);
        if (null == condition) {
            condition = new OrderCondition();
        }
        PageInfo<OrderDTO> dtos = orderService.listInfo(condition);
        List<OrderDTO> list = dtos.getList();
        list.forEach(dto -> {
            dto.setBuyerId(userService.getUserById(dto.getBuyerId()).getId());
            dto.setSalerId(userService.getUserById(dto.getSalerId()).getId());
        });
        return OperationResult.succ(
                PageInfoUtils.pageInfoTrans(dtos, OrderTransUtils::dto2vo));
    }

    @PostMapping(value = "/add/{salerId}/{buyerId}/{goodsId}")
    public Result addTrade(@PathVariable("salerId") Integer salerId,
                           @PathVariable("buyerId") Integer buyerId,
                           @PathVariable("goodsId") Integer goodsId){
        OrderCreateDTO create = new OrderCreateDTO();
        create.setSalerId(salerId);
        create.setBuyerId(buyerId);
        create.setGoodsId(goodsId);
        orderService.save(create);
        return ResultUtil.success(ResultEnum.ADD_TRADE);
    }

    @GetMapping(value = "/status/{buyerId}/{status}")
    public Result<List<GoodVO>> getGoodsListByBuyerIdAndStatus(@PathVariable("buyerId") Integer buyerId,
                                                               @PathVariable("status") Integer status){
        OrderCondition condition = new OrderCondition();
        condition.setBuyerId(buyerId);
        List<Integer> statusList = new ArrayList<>();
        statusList.add(status);
        condition.setOrderStatus(statusList);
        PageInfo<OrderDTO> orderDTOPageInfo = orderService.listInfo(condition);
        List<OrderDTO> orders = orderDTOPageInfo.getList();
        List<GoodVO> goods = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(orders)) {
            for (OrderDTO order : orders) {
                goods.add(GoodTransUtils.dto2vo(goodService.getById(order.getGoodsId())));
            }
        }
        return ResultUtil.success(ResultEnum.GET_ONES_BOUGHT_GOODS,goods);
    }
}

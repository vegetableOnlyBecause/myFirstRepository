package com.example.controller.good;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.OprUtils;
import com.example.condition.GoodCondition;
import com.example.condition.OrderCondition;
import com.example.controller.good.util.GoodTransUtils;
import com.example.controller.good.vo.GoodCreate;
import com.example.controller.good.vo.GoodVO;
import com.example.good.GoodService;
import com.example.good.TypeService;
import com.example.good.dto.GoodDTO;
import com.example.good.dto.GoodOprDTO;
import com.example.good.dto.TypeDTO;
import com.example.good.util.PageInfoUtils;
import com.example.order.OrderService;
import com.example.order.dto.OrderDTO;
import com.example.response.OperationResult;
import com.example.response.Result;
import com.example.response.ResultEnum;
import com.example.response.ResultUtil;
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
 * @title: 商品Controller
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 18:56
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/market/goods")
public class GoodController {

    @Resource
    private GoodService goodService;
    @Resource
    private TypeService typeService;
    @Resource
    private OrderService orderService;

    @PostMapping(value="/add")
    public Result<Object> add(@Validated @RequestBody GoodCreate params) {
        Integer id = goodService.save(GoodTransUtils.vo2dto(params));
        return ResultUtil.success(ResultEnum.ADD_GOODS, id);
    }

    @PostMapping(value = "/delete/{id}")
    public Result deleteGoodsById(@PathVariable("id") Integer id){
        OrderCondition condition = new OrderCondition();
        condition.setGoodId(id);
        PageInfo<OrderDTO> orderDTOPageInfo = orderService.listInfo(condition);
        List<OrderDTO> orders = orderDTOPageInfo.getList();
        if (CollectionUtils.isNotEmpty(orders)) {
            orders.removeIf(x -> 4 == x.getStatus() || 5 == x.getStatus());
            if (CollectionUtils.isNotEmpty(orders)) {
                return ResultUtil.error(ResultEnum.DELETE_GOODS_ERROR);
            }
        }
        goodService.delById(id);
        return ResultUtil.success(ResultEnum.DELETE_GOODS);
    }

    @RequestMapping(method = RequestMethod.GET)
    public OperationResult<Object> listInfo(@RequestParam Map<String, String> param) {
        GoodCondition condition = JSON.parseObject(JSON.toJSONString(param), GoodCondition.class);
        if (null == condition) {
            condition = new GoodCondition();
        }
        PageInfo<GoodDTO> dtos = goodService.listInfo(condition);
        return OperationResult.succ(PageInfoUtils.pageInfoTrans(dtos, GoodTransUtils::dto2vo));
    }

    @GetMapping(value = "/count/all")
    public Result<Long> getGoodsCount(){
        PageInfo<GoodDTO> goods = goodService.listInfo(new GoodCondition());
        return ResultUtil.success(ResultEnum.GET_GOODS_COUNT,goods.getTotal());
    }

    @GetMapping(value = "/goods-list/{page}")
    public Result<List<GoodVO>> getAllGoodsList(@PathVariable("page") int page){
        GoodCondition condition = new GoodCondition();
        condition.setPage(page);
        condition.setPageSize(10);
        PageInfo<GoodDTO> dtos = goodService.listInfo(condition);
        PageInfo<GoodVO> vos = PageInfoUtils.pageInfoTrans(dtos, GoodTransUtils::dto2vo);
        List<GoodVO> result = vos.getList();
        result.removeIf(good -> !(good.getNum() > 0));
        return ResultUtil.success(ResultEnum.GET_GOODS_INFO_LIST, vos.getList());
    }

    @GetMapping("/{type}/{page}")
    public Result<List<GoodVO>> getGoodsListByType(@PathVariable("type") String type,
                                                  @PathVariable("page") int page) {
        GoodCondition condition = new GoodCondition();
        condition.setPage(page);
        condition.setPageSize(10);
        TypeDTO typeDTO = typeService.getByName(type);
        List<GoodVO> goods = new ArrayList<>();
        if (null != typeDTO) {
            condition.setTypeId(typeDTO.getId());
            PageInfo<GoodDTO> dtos = goodService.listInfo(condition);
            PageInfo<GoodVO> vos = PageInfoUtils.pageInfoTrans(dtos, GoodTransUtils::dto2vo);
            goods = vos.getList();
        }

        return ResultUtil.success(ResultEnum.GET_GOODS_INFO_LIST, goods);
    }

    @GetMapping(value = "/content/{goodsId}")
    public Result<GoodVO> getOneGoodsById(@PathVariable("goodsId") Integer goodsId){
        GoodDTO good = goodService.getById(goodsId);
        return ResultUtil.success(ResultEnum.GET_GOODS_DETAIL,GoodTransUtils.dto2vo(good));
    }

    /* 通过用户 ID 获取用户发布的所有闲置商品 */
    @GetMapping(value = "/user/{userId}")
    public Result<List<GoodVO>> getGoodsListByUserId(@PathVariable("userId") Integer userId){
        GoodCondition condition = new GoodCondition();
        condition.setUserId(userId);
        PageInfo<GoodDTO> dtoPageInfo = goodService.listInfo(condition);
        return ResultUtil.success(ResultEnum.GET_USER_GOODS_LIST,
                OprUtils.models2Models(dtoPageInfo.getList(), GoodTransUtils::dto2vo));
    }


    @PostMapping(value = "/change/status/{goodsId}")
    public Result changeGoodsStatus(@PathVariable("goodsId") Integer goodsId){
        GoodDTO good = goodService.getById(goodsId);
        GoodOprDTO opr = new GoodOprDTO();
        opr.setId(goodsId);
        opr.setStatus(good.getStatus() == 0 ? 1 : 0);
        goodService.update(opr);
        return ResultUtil.success(ResultEnum.UPDATE_GOODS);
    }

    @GetMapping(value = "/search/{key}/{page}")
    public Result<List<GoodVO>> searchGoodsByKey(@PathVariable("key") String key,
                                                @PathVariable("page") int page){
        GoodCondition condition = new GoodCondition();
        condition.setPage(page);
        condition.setPageSize(10);
        condition.setName(key);
        PageInfo<GoodDTO> dto = goodService.listInfo(condition);
        return ResultUtil.success(ResultEnum.GET_GOODS_BY_KEY,
                OprUtils.models2Models(dto.getList(), GoodTransUtils::dto2vo));
    }
}

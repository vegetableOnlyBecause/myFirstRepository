package com.example.controller.good;

import com.alibaba.fastjson.JSON;
import com.example.condition.TypeCondition;
import com.example.controller.good.vo.GoodVO;
import com.example.good.GoodService;
import com.example.good.TypeService;
import com.example.good.dto.GoodDTO;
import com.example.good.dto.TypeDTO;
import com.example.good.util.PageInfoUtils;
import com.example.condition.GoodCondition;
import com.example.controller.good.util.GoodTransUtils;
import com.example.controller.good.vo.GoodCreate;
import com.example.response.OperationResult;
import com.example.response.Result;
import com.example.response.ResultEnum;
import com.example.response.ResultUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PostMapping(value="/add")
    public Result<Object> add(@Validated @RequestBody GoodCreate params) {
        Integer id = goodService.save(GoodTransUtils.vo2dto(params));
        return ResultUtil.success(ResultEnum.ADD_GOODS, id);
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
        return ResultUtil.success(ResultEnum.GET_GOODS_INFO_LIST, vos.getList());
    }

    @GetMapping("/{type}/{page}")
    public Result<List<GoodVO>> getGoodsListByType(@PathVariable("type") String type,
                                                  @PathVariable("page") int page) {

        GoodCondition condition = new GoodCondition();
        condition.setPage(page);
        condition.setPageSize(10);
        TypeDTO typeDTO = typeService.getByName(type);
        condition.setTypeId(typeDTO.getId());
        PageInfo<GoodDTO> dtos = goodService.listInfo(condition);
        PageInfo<GoodVO> vos = PageInfoUtils.pageInfoTrans(dtos, GoodTransUtils::dto2vo);
        return ResultUtil.success(ResultEnum.GET_GOODS_INFO_LIST, vos.getList());
    }

    @GetMapping(value = "/content/{goodsId}")
    public Result<GoodVO> getOneGoodsById(@PathVariable("goodsId") Integer goodsId){
        GoodDTO good = goodService.getById(goodsId);
        return ResultUtil.success(ResultEnum.GET_GOODS_DETAIL,GoodTransUtils.dto2vo(good));
    }
}

package com.example.controller.commodity;

import com.alibaba.fastjson.JSON;
import com.example.commodity.CommodityService;
import com.example.commodity.dto.CommodityDTO;
import com.example.commodity.util.PageInfoUtils;
import com.example.condition.CommodityCondition;
import com.example.controller.commodity.util.CommodityTransUtils;
import com.example.controller.commodity.vo.CommodityCreate;
import com.example.response.OperationResult;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @title: 商品Controller
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 18:56
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/commodity")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public OperationResult<Object> create(@Validated @RequestBody CommodityCreate create) {
        return OperationResult.succ(commodityService.save(CommodityTransUtils.vo2dto(create)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public OperationResult<Object> listInfo(@RequestParam Map<String, String> param) {
        CommodityCondition condition = JSON.parseObject(JSON.toJSONString(param), CommodityCondition.class);
        if (null == condition) {
            condition = new CommodityCondition();
        }
        PageInfo<CommodityDTO> dtos = commodityService.listInfo(condition);
        return OperationResult.succ(PageInfoUtils.pageInfoTrans(dtos, CommodityTransUtils::dto2vo));
    }
}

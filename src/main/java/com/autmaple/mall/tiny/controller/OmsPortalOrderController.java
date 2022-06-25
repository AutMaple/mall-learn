package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.dto.OrderParam;
import com.autmaple.mall.tiny.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName OmsPortalOrderController
 * @Description 订单管理 Controller
 * @Author AutMaple
 * @Date 2022/6/25 12:13
 * @Version 1.0
 **/
@Controller
@Api(tags = "OmsPortalOrderController", description = "订单管理接口")
@RequestMapping("/order")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("根据购物车的信息生成订单")
    @ResponseBody
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public Object generatorOrder(@RequestBody OrderParam orderParam){
        return omsPortalOrderService.generatorOrder(orderParam);
    }
}

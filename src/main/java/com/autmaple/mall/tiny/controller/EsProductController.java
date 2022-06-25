package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.autmaple.mall.tiny.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

/**
 * @ClassName EsProductController
 * @Description  搜索商品管理的 Controller
 * @Author AutMaple
 * @Date 2022/6/19 21:03
 * @Version 1.0
 **/
@Controller
@Api(value = "EsProductController", description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    /**
     * @Author AutMaple
     * @Description 导入所有数据库中的商品到 ES 中
     * @Date 2022/6/19 21:09
     **/
    @ApiOperation("导入所有数据库中的商品到 ES 中")
    @ResponseBody
    @RequestMapping(value="/importAll", method = RequestMethod.POST)
    public CommonResult<Integer> importAllList(){
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    /**
     * @Author AutMaple
     * @Description 根据 id 删除商品
     * @Date 2022/6/19 21:12
     **/
    @ApiOperation(value = "根据 id 删除商品")
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult<Object> delete(@PathVariable Long id){
        esProductService.delete(id);
        return CommonResult.success(null);
    }


    /**
     * @Author AutMaple
     * @Description 批量删除商品
     * @Date 2022/6/19 21:16
     **/
    @ApiOperation(value = "批量删除商品")
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids){
        esProductService.delete(ids);
        return CommonResult.success(null);
    }


    /**
     * @Author AutMaple
     * @Description 根据 id 创建商品
     * @Date 2022/6/19 21:13
     * @return 创建成功之后商品对应的实例对象 EsProduct
     **/
    @ApiOperation(value = "根据 id 创建商品")
    @ResponseBody
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public CommonResult<EsProduct> create(@PathVariable Long id){
        EsProduct product = esProductService.create(id);
        if(product != null)
            return CommonResult.success(product);
        return CommonResult.failed();
    }

    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }
}

package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.autmaple.mall.tiny.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName EsProductController
 * @Description 搜索商品管理的 Controller
 * @Author AutMaple
 * @Date 2022/6/19 21:03
 * @Version 1.0
 **/
@Api(value = "EsProductController", description = "搜索商品管理")
@RestController
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
    @PostMapping("/importAll")
    public CommonResult<Integer> importAllList() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    /**
     * @Author AutMaple
     * @Description 根据 id 删除商品
     * @Date 2022/6/19 21:12
     **/
    @ApiOperation(value = "根据 id 删除商品")
    @GetMapping("/delete/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }


    /**
     * @Author AutMaple
     * @Description 批量删除商品
     * @Date 2022/6/19 21:16
     **/
    @ApiOperation(value = "批量删除商品")
    @PostMapping("/delete")
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }


    /**
     * @return 创建成功之后商品对应的实例对象 EsProduct
     * @Author AutMaple
     * @Description 根据 id 创建商品
     * @Date 2022/6/19 21:13
     **/
    @ApiOperation(value = "根据 id 创建商品")
    @PostMapping("/create/{id}")
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        // TODO 根据 id 创建商品有问题，接口一直返回创建失败
        EsProduct product = esProductService.create(id);
        if (product != null)
            return CommonResult.success(product);
        return CommonResult.failed();
    }

    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }
}

package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.PmsSkuStock;
import com.autmaple.mall.tiny.service.PmsSkuStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PmsSkuStockController
 * @Description 商品 sku 库存管理 Controller
 * @Author AutMaple
 * @Date 2022/7/17 09:42
 * @Version 1.0
 **/
@Tag(description = "PmsSkuStockController", name = "sku 商品库存管理")
@RestController
@RequestMapping("/sku")
public class PmsSkuStockController {

    @Autowired
    private PmsSkuStockService skuStockService;

    @Operation(summary="根据商品 ID 和 sku 编码模糊搜索 sku 库存")
    @GetMapping("/{pid}")
    public CommonResult<List<PmsSkuStock>> getList(@PathVariable Long pid,
                                                   @RequestParam(value = "keyword", required = false) String keyword){
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return CommonResult.success(skuStockList);
    }

    @Operation(summary="批量更新 sku 库存信息")
    @PostMapping("/update/{pid}")
    public CommonResult update(@PathVariable Long pid,
                               @RequestBody List<PmsSkuStock> skuStockList){
        int count = skuStockService.update(pid, skuStockList);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}

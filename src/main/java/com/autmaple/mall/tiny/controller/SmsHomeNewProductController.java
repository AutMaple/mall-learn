package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.SmsHomeNewProduct;
import com.autmaple.mall.tiny.service.SmsHomeNewProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SmsHomeNewProductController
 * @Description 首页新品管理 Controller
 * @Author AutMaple
 * @Date 2022/7/19 20:54
 * @Version 1.0
 **/
@Tag( description = "SmsHomeNewProductController", name = "首页新品管理")
@RestController
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {

    @Autowired
    private SmsHomeNewProductService productService;

    @Operation(summary="添加首页新品")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsHomeNewProduct> productList) {
        int count = productService.create(productList);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改新品排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = productService.updateSort(id, sort);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量删除新品")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = productService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改新品推荐状态")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="分页查询新品")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeNewProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeNewProduct> productList = productService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }

}

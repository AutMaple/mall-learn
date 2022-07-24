package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.SmsHomeRecommendProduct;
import com.autmaple.mall.tiny.service.SmsHomeRecommendProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SmsHomeRecommendProductController
 * @Description 首页人气推荐管理 Controller
 * @Author AutMaple
 * @Date 2022/7/19 21:22
 * @Version 1.0
 **/
@Tag(description = "SmsHomeRecommendProductController", name = "首页人气推荐管理")
@RestController
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductService productService;

    @Operation(summary="添加人气推荐商品")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsHomeRecommendProduct> productList) {
        int count = productService.create(productList);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改人气商品的排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable Long id, @RequestParam Integer sort) {
        int count = productService.updateSort(id, sort);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量删除人气商品")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = productService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改人气商品的推荐状态")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="分页查询人气推荐商品")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeRecommendProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRecommendProduct> productList = productService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }
}

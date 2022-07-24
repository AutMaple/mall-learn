package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.PmsProductParam;
import com.autmaple.mall.tiny.dto.PmsProductQueryParam;
import com.autmaple.mall.tiny.dto.PmsProductResult;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.service.PmsProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PmsProductController
 * @Description 商品管理 Controller
 * @Author AutMaple
 * @Date 2022/7/16 19:12
 * @Version 1.0
 **/
@Tag(description = "PmsProductController", name = "商品管理")
@RestController
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService productService;

    @Operation(summary="创建商品")
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsProductParam productParam) {
        int count = productService.create(productParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="根据商品 id 获取商品信息编辑信息")
    @GetMapping("/updateInfo/{id}")
    public CommonResult<PmsProductResult> getUpdateInfo(@PathVariable Long id) {
        PmsProductResult result = productService.getUpdateInfo(id);
        return CommonResult.success(result);
    }

    @Operation(summary="更新商品")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductParam productParam) {
        int count = productService.update(id, productParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="查询商品")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductQueryParam productQueryParam,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = productService.list(productQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @Operation(summary="根据商品名称或者货号模糊查询")
    @GetMapping("/simpleList")
    public CommonResult<List<PmsProduct>> getList(String keyword) {
        List<PmsProduct> productList = productService.list(keyword);
        return CommonResult.success(productList);
    }

    @Operation(summary="批量修改审批状态")
    @PostMapping("/update/verifyStatsu")
    public CommonResult updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("verifyStatus") Integer verifyStatus,
                                           @RequestParam("detail") String detail) {
        int count = productService.updateVerifyStatus(ids, verifyStatus, detail);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量上下架商品")
    @PostMapping("/update/publishStatus")
    public CommonResult updatePublicStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("publishStatus") Integer publishStatus) {
        int count = productService.updatePublishStatus(ids, publishStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }


    @Operation(summary="批量推荐商品")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量设置新品")
    @PostMapping("/update/newStatus")
    public CommonResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("newStatus") Integer newStatus) {
        int count = productService.updateNewStatus(ids, newStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量修改删除状态")
    @PostMapping("update/deleteStatus")
    public CommonResult updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

}

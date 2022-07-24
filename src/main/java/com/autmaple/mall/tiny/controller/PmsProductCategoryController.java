package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.PmsProductCategoryParam;
import com.autmaple.mall.tiny.dto.PmsProductCategoryWithChildrenItem;
import com.autmaple.mall.tiny.mbg.model.PmsProductCategory;
import com.autmaple.mall.tiny.service.PmsProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simpleframework.xml.core.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PmsProductCategoryController
 * @Description 商品分类管理 Controller
 * @Author AutMaple
 * @Date 2022/7/16 16:51
 * @Version 1.0
 **/
@Tag(description = "PmsProductCategoryController", name = "商品分类管理")
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService productCategoryService;

    @Operation(summary="创建商品分类")
    @PostMapping("/create")
    public CommonResult create(@Validate @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.create(productCategoryParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="更新商品分类信息")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.update(id, productCategoryParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="分页查询商品分类")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductCategory> categoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(categoryList));
    }

    @Operation(summary="获取指定分类的详细信息")
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getItem(@PathVariable Long id) {
        PmsProductCategory category = productCategoryService.getItem(id);
        return CommonResult.success(category);
    }

    @Operation(summary="删除商品分类")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改导航栏显示状态")
    @PostMapping("/update/navStatus")
    public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("navStatus") Integer navStatus) {
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改展示状态")
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus) {
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="查询所有一级分类及子分类")
    @GetMapping("/list/withChildren")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return CommonResult.success(list);
    }

}

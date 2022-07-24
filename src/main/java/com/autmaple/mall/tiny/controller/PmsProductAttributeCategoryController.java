package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.PmsProductAttributeCategoryItem;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttributeCategory;
import com.autmaple.mall.tiny.service.PmsProductAttributeCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PmsProductAttributeCategoryController
 * @Description 商品属性分类管理 Controller
 * @Author AutMaple
 * @Date 2022/7/16 12:12
 * @Version 1.0
 **/
@Tag(description = "PmsProductAttributeCategoryController", name = "商品书信分类管理")
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @Operation(summary="添加商品分类")
    @PostMapping("/create")
    public CommonResult create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改商品属性分类")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestParam String name) {
        int count = productAttributeCategoryService.update(id, name);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="删除整个商品属性分类")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="获取单个商品属性分类")
    @GetMapping("{id}")
    public CommonResult<PmsProductAttributeCategory> getItem(@PathVariable Long id) {
        PmsProductAttributeCategory item = productAttributeCategoryService.getItem(id);
        return CommonResult.success(item);
    }

    @Operation(summary="分页获取所有商品属性分类")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(defaultValue = "5") Integer pageSize,
                                                                         @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategory> attributeList = productAttributeCategoryService.getList(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(attributeList));
    }

    @Operation(summary="获取所有上屏属性分类及其下属性")
    @GetMapping("/list/withAttr")
    public CommonResult<List<PmsProductAttributeCategoryItem>> getListWithAttr() {
        List<PmsProductAttributeCategoryItem> productAttributeCategoryItemList = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(productAttributeCategoryItemList);
    }
}

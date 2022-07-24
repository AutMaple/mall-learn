package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.UmsResourceCategory;
import com.autmaple.mall.tiny.service.UmsResourceCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsResourceCategoryController
 * @Description 后台资源分类管理 Controller
 * @Author AutMaple
 * @Date 2022/7/20 21:17
 * @Version 1.0
 **/
@Tag(description = "UmsResourceCategoryController", name = "后台资源分类管理")
@RestController
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {

    @Autowired
    private UmsResourceCategoryService categoryService;

    @Operation(summary="查询所有后台资源分类")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResourceCategory>> listAll() {
        List<UmsResourceCategory> resourceCategoryList = categoryService.listAll();
        return CommonResult.success(resourceCategoryList);
    }

    @Operation(summary="创建后台资源分类")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResourceCategory category) {
        int count = categoryService.create(category);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="更新后台资源分类")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResourceCategory resourceCategory) {
        int count = categoryService.update(id, resourceCategory);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="根据 ID 删除后台资源")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = categoryService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}

package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.UmsMenuNode;
import com.autmaple.mall.tiny.mbg.model.UmsMenu;
import com.autmaple.mall.tiny.service.UmsMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsMenuController
 * @Description 后台菜单管理 Controller
 * @Author AutMaple
 * @Date 2022/7/20 20:38
 * @Version 1.0
 **/
@Tag(description = "UmsMenuController", name = "后台菜单管理")
@RestController
@RequestMapping("/menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService menuService;

    @Operation(summary="添加后台菜单")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsMenu menu) {
        int count = menuService.create(menu);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="更新后台菜单")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsMenu menu) {
        int count = menuService.update(id, menu);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="删除后台菜单")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = menuService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="分页查询后台菜单")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsMenu> menuList = menuService.list(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(menuList));
    }

    @Operation(summary="树形结构返回所有菜单列表")
    @GetMapping("/treeList")
    public CommonResult<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> menuNodeList = menuService.treeList();
        return CommonResult.success(menuNodeList);
    }

    @Operation(summary="修改菜单的显示状态")
    @PostMapping("/updateHidden/{id}")
    public CommonResult updateHidden(@PathVariable Long id,
                                     @RequestParam("hidden") Integer hidden) {
        int count = menuService.updateHidden(id, hidden);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}

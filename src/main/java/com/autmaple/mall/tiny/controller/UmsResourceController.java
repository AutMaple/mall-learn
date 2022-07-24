package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.component.DynamicSecurityMetaDataSource;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import com.autmaple.mall.tiny.service.UmsResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsResourceController
 * @Description 后台资源管理 Controller
 * @Author AutMaple
 * @Date 2022/7/20 21:31
 * @Version 1.0
 **/
@Tag(description = "UmsResourceController", name = "后台资源管理")
@RestController
@RequestMapping("/resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService resourceService;

    @Autowired
    private DynamicSecurityMetaDataSource dynamicSecurityMetaDataSource;

    @Operation(summary="添加后台资源")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResource umsResource) {
        int count = resourceService.create(umsResource);
        dynamicSecurityMetaDataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改后台资源")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResource umsResource) {
        int count = resourceService.update(id, umsResource);
        dynamicSecurityMetaDataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="根据ID获取资源详情")
    @GetMapping("/{id}")
    public CommonResult<UmsResource> getItem(@PathVariable Long id) {
        UmsResource umsResource = resourceService.getItem(id);
        return CommonResult.success(umsResource);
    }

    @Operation(summary="根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = resourceService.delete(id);
        dynamicSecurityMetaDataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="分页模糊查询后台资源")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResource> resourceList = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @Operation(summary="查询所有后台资源")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResource>> listAll() {
        List<UmsResource> resourceList = resourceService.listAll();
        return CommonResult.success(resourceList);
    }
}

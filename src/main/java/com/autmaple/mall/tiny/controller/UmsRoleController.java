package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.UmsMenu;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import com.autmaple.mall.tiny.mbg.model.UmsRole;
import com.autmaple.mall.tiny.service.UmsRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsRoleController
 * @Description 后台用户角色管理 Controller
 * @Author AutMaple
 * @Date 2022/7/22 20:38
 * @Version 1.0
 **/
@Tag(description = "UmsRoleController", name = "后台用户角色管理")
@RestController
@RequestMapping("/role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService roleService;


    @Operation(summary="创建后台角色")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsRole role){
        int count = roleService.create(role);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量删除角色")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
        int count = roleService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="获取所有角色")
    @GetMapping("/listAll")
    public CommonResult<List<UmsRole>> listAll() {
        List<UmsRole> roleList = roleService.list();
        return CommonResult.success(roleList);
    }

    @Operation(summary="分页查询所有角色")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<UmsRole> roleList = roleService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @Operation(summary="修改角色状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable Long id,
                                     @RequestParam(value = "status") Integer status){
        UmsRole role = new UmsRole();
        role.setStatus(status);
        int count = roleService.update(id, role);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="获取角色相关菜单")
    @GetMapping("/listMenu/{roleId}")
    public CommonResult<List<UmsMenu>> listMenu(@PathVariable Long roleId) {
        List<UmsMenu> roleList = roleService.listMenu(roleId);
        return CommonResult.success(roleList);
    }

    @Operation(summary="获取角色相关资源")
    @GetMapping("/listResource/{roleId}")
    public CommonResult<List<UmsResource>> listResource(@PathVariable Long roleId) {
        List<UmsResource> roleList = roleService.listResource(roleId);
        return CommonResult.success(roleList);
    }

    @Operation(summary="给角色分配菜单")
    @PostMapping("/allocMenu")
    public CommonResult allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        return CommonResult.success(count);
    }

    @Operation(summary="给角色分配资源")
    @PostMapping("/allocResource")
    public CommonResult allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        return CommonResult.success(count);
    }
}

package com.autmaple.mall.tiny.controller;

import cn.hutool.core.collection.CollUtil;
import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.UmsAdminLoginParam;
import com.autmaple.mall.tiny.dto.UpdateAdminPasswordParam;
import com.autmaple.mall.tiny.mbg.model.UmsAdmin;
import com.autmaple.mall.tiny.mbg.model.UmsRole;
import com.autmaple.mall.tiny.service.UmsAdminService;
import com.autmaple.mall.tiny.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UmsAdminController
 * @Description 管理员相关操作的接口
 * @Author AutMaple
 * @Date 2022/6/15 20:25
 * @Version 1.0
 **/
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    private final UmsAdminService adminService;

    @Autowired
    private UmsRoleService roleService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public UmsAdminController(UmsAdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * @return 注册成功之后管理员的信息
     * @Author AutMaple
     * @Description 管理员注册接口
     * @Date 20:35 2022/6/15
     * @Param UmsAdminParam 前台传递的管理员信息
     **/
    @ApiOperation("管理员注册接口")
    @PostMapping("/register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null)
            return CommonResult.failed();
        return CommonResult.success(umsAdmin);
    }

    /**
     * @return 登录成功之后返回对应的 token 字符串
     * @Author AutMaple
     * @Description 管理员登录接口
     * @Date 21:36 2022/6/15
     **/
    @ApiOperation("管理员登录接口")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null)
            return CommonResult.validateFailed("用户名或密码错误");
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("刷新Token")
    @GetMapping("/refreshToken")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = adminService.refreshToken(token);
        if (refreshedToken == null)
            return CommonResult.failed("Token 已过期");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", refreshedToken);
        map.put("tokenHead", tokenHead);
        return CommonResult.success(map);
    }

    @ApiOperation("展示用户信息")
    @GetMapping("/info")
    public CommonResult getAdminInfo(Principal principal) {
        if (principal == null)
            return CommonResult.unauthorized(null);
        String username = principal.getName();
        UmsAdmin admin = adminService.getAdminByUsername(username);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", admin.getUsername());
        map.put("menus", roleService.getMenuList(admin.getId()));
        map.put("icon", admin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(admin.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream()
                    .map(UmsRole::getName)
                    .collect(Collectors.toList());
            map.put("roles", roles);
        }
        return CommonResult.success(map);
    }

    @ApiOperation("退出功能")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation("根据用户名或者姓名分页获取用户列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }


    @ApiOperation("获取指定用户的用户信息")
    @GetMapping("/{id}")
    public CommonResult<UmsAdmin> getItem(@PathVariable("id") Long id) {
        UmsAdmin admin = adminService.getItem(id);
        return CommonResult.success(admin);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody UmsAdmin admin) {
        int count = adminService.update(id, admin);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改用户密码")
    @PostMapping("/updatePassword")
    public CommonResult updatePassword(@Valid @RequestBody UpdateAdminPasswordParam param) {
        int status = adminService.updatePassword(param);
        if (status > 0)
            return CommonResult.success(status);
        if (status == -1)
            return CommonResult.failed("提交的参数不合法");
        if (status == -2)
            return CommonResult.failed("找不到对应的用户");
        if (status == -3)
            return CommonResult.failed("旧密码不正确");
        return CommonResult.failed();

    }

    @ApiOperation("修改帐号的状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable("id") Long id, @RequestParam(value = "status") Integer status) {
        UmsAdmin admin = new UmsAdmin();
        admin.setStatus(status);
        int count = adminService.update(id, admin);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @PostMapping("/role/update")
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("获取用户的角色列表")
    @GetMapping("/role/{adminId}")
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable("adminId") Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }
}

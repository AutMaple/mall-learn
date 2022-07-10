package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.UmsAdminLoginParam;
import com.autmaple.mall.tiny.mbg.model.UmsAdmin;
import com.autmaple.mall.tiny.mbg.model.UmsPermission;
import com.autmaple.mall.tiny.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * ClassName UmsAdminController
 * Description 管理员相关操作的接口
 *
 * @Author AutMaple
 * @Date 2022/6/15 20:25
 * Version 1.0
 **/
@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    private final UmsAdminService adminService;

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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null)
            return CommonResult.validateFailed("用户名或密码错误");
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    /**
     * @return 返回对应管理员的权限列表
     * @Author AutMaple
     * @Description 获取某个管理员的权限列表
     * @Date 21:38 2022/6/15
     * @Param adminId 管理员的唯一标识
     **/
    @ApiOperation("返回对应管理员的权限列表")
    @ResponseBody
    @RequestMapping(value = "/permissions/{adminId}", method = RequestMethod.GET)
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }

}

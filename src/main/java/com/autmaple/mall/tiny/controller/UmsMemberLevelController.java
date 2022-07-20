package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.UmsMemberLevel;
import com.autmaple.mall.tiny.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UmsMemberLevelController
 * @Description 会员等级管理 Controller
 * @Author AutMaple
 * @Date 2022/7/20 20:27
 * @Version 1.0
 **/
@Api(tags = "UmsMemberLevelController", description = "会员等级管理")
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    private UmsMemberLevelService memberLevelService;

    @ApiOperation("查询所有的会员等级")
    @GetMapping("/list")
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return CommonResult.success(memberLevelList);
    }
}

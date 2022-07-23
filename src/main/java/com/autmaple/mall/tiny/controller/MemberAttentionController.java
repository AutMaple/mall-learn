package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.nosql.mongodb.document.MemberBrandAttention;
import com.autmaple.mall.tiny.service.MemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MemberAttentionController
 * @Description 会员关注商品管理 Controller
 * @Author AutMaple
 * @Date 2022/7/23 11:38
 * @Version 1.0
 **/
@Api(tags = "MemberAttentionController", description = "会员关注商品管理")
@RestController
@RequestMapping("/member/attention")
public class MemberAttentionController {

    @Autowired
    private MemberAttentionService memberAttentionService;

    @ApiOperation("添加品牌关注")
    @PostMapping("/add")
    public CommonResult add(@RequestBody MemberBrandAttention memberBrandAttention) {
        int count = memberAttentionService.add(memberBrandAttention);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("取消品牌关注")
    @PostMapping("/delete")
    public CommonResult delete(Long brandId) {
        int count = memberAttentionService.delete(brandId);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("显示当前用户品牌关注列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<MemberBrandAttention>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<MemberBrandAttention> page = memberAttentionService.list(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("显示品牌关注详情")
    @GetMapping("/detail")
    public CommonResult<MemberBrandAttention> detail(@RequestParam Long brandId) {
        MemberBrandAttention memberBrandAttention = memberAttentionService.detail(brandId);
        return CommonResult.success(memberBrandAttention);
    }

    @ApiOperation("清空当前用户品牌关注列表")
    @PostMapping("/clear")
    public CommonResult clear() {
        memberAttentionService.clear();
        return CommonResult.success(null);
    }
}

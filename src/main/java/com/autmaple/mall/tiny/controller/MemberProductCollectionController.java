package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.nosql.mongodb.document.MemberProductCollection;
import com.autmaple.mall.tiny.service.MemberProductCollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MemberProductCollectionController
 * @Description 会员商品收藏管理
 * @Author AutMaple
 * @Date 2022/7/23 15:25
 * @Version 1.0
 **/
@Tag(description = "MemberProductCollectionController", name = "会员收藏管理")
@RestController
@RequestMapping("/member/productCollection")
public class MemberProductCollectionController {

    @Autowired
    private MemberProductCollectionService memberCollectionService;

    @Operation(summary="添加商品收藏")
    @PostMapping("/add")
    public CommonResult add(@RequestBody MemberProductCollection productCollection) {
        int count = memberCollectionService.add(productCollection);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="删除商品收藏")
    @PostMapping("/delete")
    public CommonResult delete(Long productId) {
        int count = memberCollectionService.delete(productId);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="显示当前用户商品收藏列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<MemberProductCollection>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<MemberProductCollection> page = memberCollectionService.list(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @Operation(summary="显示商品收藏详情")
    @GetMapping("/detail")
    public CommonResult<MemberProductCollection> detail(@RequestParam Long productId) {
        MemberProductCollection memberProductCollection = memberCollectionService.detail(productId);
        return CommonResult.success(memberProductCollection);
    }

    @Operation(summary="清空当前用户商品收藏列表")
    @PostMapping("/clear")
    public CommonResult clear() {
        memberCollectionService.clear();
        return CommonResult.success(null);
    }
}

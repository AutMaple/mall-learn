package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import com.autmaple.mall.tiny.service.MemberReadHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MemberReadHistoryController
 * @Description 会员商品浏览记录管理 Controller
 * @Author AutMaple
 * @Date 2022/6/23 21:09
 * @Version 1.0
 **/
@Tag(description = "MemberReadHistoryController", name = "会员商品浏览记录管理")
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {
    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @Operation(summary="创建会员商品浏览记录")
    @PostMapping("/create")
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }


    @Operation(summary = "删除浏览记录")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="清空浏览记录")
    @PostMapping("/clear")
    public CommonResult clear(){
        memberReadHistoryService.clear();
        return CommonResult.success(null);
    }

    @Operation(summary="展示会员浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<Page<MemberReadHistory>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<MemberReadHistory> result = memberReadHistoryService.list(pageNum, pageSize);
        return CommonResult.success(result);
    }
}

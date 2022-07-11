package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import com.autmaple.mall.tiny.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MemberReadHistoryController
 * @Description 会员商品浏览记录管理 Controller
 * @Author AutMaple
 * @Date 2022/6/23 21:09
 * @Version 1.0
 **/
@Api(tags = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {
    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    /**
     * @Author AutMaple
     * @Description 创建会员商品浏览记录
     * @Date 2022/6/23 21:17
     **/
    @ApiOperation("创建会员商品浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    /**
     * @Author AutMaple
     * @Description TODO 删除会员浏览记录
     * @Date 2022/6/23 21:17
     **/
    @ApiOperation(value = "删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    /**
     * @Author AutMaple
     * @Description 展示会员商品浏览记录
     * @Date 2022/6/23 21:21
     **/
    @ApiOperation("展示会员浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<MemberReadHistory>> list(long memberId) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return CommonResult.success(memberReadHistoryList);
    }
}

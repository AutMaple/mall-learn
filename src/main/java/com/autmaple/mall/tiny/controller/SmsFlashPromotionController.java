package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotion;
import com.autmaple.mall.tiny.service.SmsFlashPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionController
 * @Description 限时购活动管理 Controller
 * @Author AutMaple
 * @Date 2022/7/17 13:49
 * @Version 1.0
 **/
@Api(tags = "SmsFlashPromotionController", description = "限时购活动管理")
@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionController {

    @Autowired
    private SmsFlashPromotionService flashPromotionService;

    @ApiOperation("添加限时购活动")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsFlashPromotion flashPromotion) {
        int count = flashPromotionService.create(flashPromotion);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改限时购活动")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody SmsFlashPromotion flashPromotion) {
        int count = flashPromotionService.update(id, flashPromotion);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("删除限时购活动")
    @GetMapping("delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = flashPromotionService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("获取限时购活动的详细信息")
    @GetMapping("{id}")
    public CommonResult<SmsFlashPromotion> getItem(@PathVariable Long id) {
        SmsFlashPromotion item = flashPromotionService.getItem(id);
        return CommonResult.success(item);
    }

    @ApiOperation("分页查询限时购活动")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotion>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsFlashPromotion> flashPromotionList = flashPromotionService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(flashPromotionList));
    }

}

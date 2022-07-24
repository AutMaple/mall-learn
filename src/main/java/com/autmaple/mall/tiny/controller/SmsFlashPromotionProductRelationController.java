package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.SmsFlashPromotionProduct;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionProductRelation;
import com.autmaple.mall.tiny.service.SmsFlashPromotionProductRelationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionProductRelationController
 * @Description 限时购活动和商品关系管理 Controller
 * @Author AutMaple
 * @Date 2022/7/18 20:36
 * @Version 1.0
 **/
@Tag(description = "SmsFlashPromotionProductRelationController", name = "限时购活动和商品关系")
@RestController
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {

    @Autowired
    private SmsFlashPromotionProductRelationService flashPromotionProductRelationService;


    @Operation(summary="创建限时购活动和商品的关联")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsFlashPromotionProductRelation> relationList) {
        int count = flashPromotionProductRelationService.create(relationList);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="更新限时购活动和商品的关系")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody SmsFlashPromotionProductRelation relation) {
        int count = flashPromotionProductRelationService.update(id, relation);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="删除关联")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = flashPromotionProductRelationService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="分页查询不同场次的限时购活动和商品的关系")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotionProduct>> list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                                   @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                                   @RequestParam(value = "pageSize") Integer pageSize,
                                                                   @RequestParam(value = "pageNum") Integer pageNum) {
        List<SmsFlashPromotionProduct> promotionList = flashPromotionProductRelationService.list(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(promotionList));
    }
}

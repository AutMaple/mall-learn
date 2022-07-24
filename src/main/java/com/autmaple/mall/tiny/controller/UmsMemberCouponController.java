package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.CartPromotionItem;
import com.autmaple.mall.tiny.dto.SmsCouponHistoryDetail;
import com.autmaple.mall.tiny.mbg.model.SmsCoupon;
import com.autmaple.mall.tiny.mbg.model.SmsCouponHistory;
import com.autmaple.mall.tiny.service.OmsCartItemService;
import com.autmaple.mall.tiny.service.UmsMemberCouponService;
import com.autmaple.mall.tiny.service.UmsMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsMemberCouponController
 * @Description 用户优惠券管理 Controller
 * @Author AutMaple
 * @Date 2022/7/24 11:21
 * @Version 1.0
 **/
@Tag(description = "UmsMemberCouponController", name = "用户优惠券管理")
@RestController
@RequestMapping("/member/coupon")
public class UmsMemberCouponController {
    @Autowired
    private UmsMemberCouponService memberCouponService;
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberService memberService;

    @Operation(summary="领取指定优惠券")
    @PostMapping("/add/{couponId}")
    public CommonResult add(@PathVariable Long couponId) {
        memberCouponService.add(couponId);
        return CommonResult.success(null,"领取成功");
    }

    @Operation(summary="获取会员优惠券历史列表")
    @Parameter(name = "useStatus", description = "优惠券筛选类型:0->未使用；1->已使用；2->已过期")
//    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
//            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @GetMapping("/listHistory")
    public CommonResult<List<SmsCouponHistory>> listHistory(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponHistory> couponHistoryList = memberCouponService.listHistory(useStatus);
        return CommonResult.success(couponHistoryList);
    }

    @Operation(summary="获取会员优惠券列表")
    @Parameter(name = "useStatus", description = "优惠券筛选类型:0->未使用；1->已使用；2->已过期")
//    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
//            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @GetMapping("/list")
    public CommonResult<List<SmsCoupon>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCoupon> couponList = memberCouponService.list(useStatus);
        return CommonResult.success(couponList);
    }

    @Operation(summary="获取登录会员购物车的相关优惠券")
    @Parameter(name = "type", description = "使用可用:0->不可用；1->可用")
//    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
//            defaultValue = "1", allowableValues = "0,1", paramType = "path", dataType = "integer")
    @GetMapping("/list/cart/{type}")
    public CommonResult<List<SmsCouponHistoryDetail>> listCart(@PathVariable Integer type) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberService.getCurrentMember().getId(), null);
        List<SmsCouponHistoryDetail> couponHistoryList = memberCouponService.listCart(cartPromotionItemList, type);
        return CommonResult.success(couponHistoryList);
    }

    @Operation(summary="获取当前商品相关优惠券")
    @GetMapping("/listByProduct/{productId}")
    public CommonResult<List<SmsCoupon>> listByProduct(@PathVariable Long productId) {
        List<SmsCoupon> couponHistoryList = memberCouponService.listByProduct(productId);
        return CommonResult.success(couponHistoryList);
    }
}

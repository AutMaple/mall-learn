package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.CartProduct;
import com.autmaple.mall.tiny.dto.CartPromotionItem;
import com.autmaple.mall.tiny.mbg.model.OmsCartItem;
import com.autmaple.mall.tiny.service.OmsCartItemService;
import com.autmaple.mall.tiny.service.UmsMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName OmsCartItemController
 * @Description 购物车管理 Controller
 * @Author AutMaple
 * @Date 2022/7/23 16:08
 * @Version 1.0
 **/
@Tag(description = "OmsCartItemController", name = "购物车管理")
@RestController
@RequestMapping("/cart")
public class OmsCartItemController {
    @Autowired
    private OmsCartItemService cartItemService;

    @Autowired
    private UmsMemberService memberService;

    @Operation(summary="添加商品到购物车")
    @PostMapping("/add")
    public CommonResult add(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.add(cartItem);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="获取当前会员的购物车列表")
    @GetMapping("/list")
    public CommonResult<List<OmsCartItem>> list() {
        List<OmsCartItem> cartItemList = cartItemService.list(memberService.getCurrentMember().getId());
        return CommonResult.success(cartItemList);
    }

    @Operation(summary="获取当前会员的购物车列表,包括促销信息")
    @GetMapping("/list/promotion")
    public CommonResult<List<CartPromotionItem>> listPromotion(@RequestParam(required = false) List<Long> cartIds) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberService.getCurrentMember().getId(), cartIds);
        return CommonResult.success(cartPromotionItemList);
    }

    @Operation(summary="修改购物车中指定商品的数量")
    @GetMapping("/update/quantity")
    public CommonResult updateQuantity(@RequestParam Long id,
                                       @RequestParam Integer quantity) {
        int count = cartItemService.updateQuantity(id, memberService.getCurrentMember().getId(), quantity);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="获取购物车中指定商品的规格,用于重选规格")
    @GetMapping("/getProduct/{productId}")
    public CommonResult<CartProduct> getCartProduct(@PathVariable Long productId) {
        CartProduct cartProduct = cartItemService.getCartProduct(productId);
        return CommonResult.success(cartProduct);
    }

    @Operation(summary="修改购物车中商品的规格")
    @PostMapping("/update/attr")
    public CommonResult updateAttr(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.updateAttr(cartItem);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="删除购物车中的指定商品")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = cartItemService.delete(memberService.getCurrentMember().getId(), ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="清空当前会员的购物车")
    @PostMapping("/clear")
    public CommonResult clear() {
        int count = cartItemService.clear(memberService.getCurrentMember().getId());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.CartProduct;
import com.autmaple.mall.tiny.dto.CartPromotionItem;
import com.autmaple.mall.tiny.mbg.model.OmsCartItem;

import java.util.List;

/**
 * @ClassName OmsCartItemService
 * @Description 购物车管理 Service
 * @Author AutMaple
 * @Date 2022/7/23 16:10
 * @Version 1.0
 **/
public interface OmsCartItemService {
    /**
     * @Author AutMaple
     * @Description 查询购物车中是否包含该商品，有增加数量，无则添加到购物车中
     * @Date 2022/7/23 16:11
     **/
    int add(OmsCartItem cartItem);

    /**
     * @Author AutMaple
     * @Description 查询用户的购物车列表
     * @Date 2022/7/23 16:11
     **/
    List<OmsCartItem> list(Long memberId);

    /**
     * @Author AutMaple
     * @Description 获取包含促销活动信息的购物车列表
     * @Date 2022/7/23 16:13
     **/
    List<CartPromotionItem> listPromotion(Long memberId, List<Long> cartIds);
    
    /**
     * @Author AutMaple
     * @Description 修改某个购物车商品的数量
     * @Date 2022/7/23 16:17
     **/
    int updateQuantity(Long id, Long memberId, Integer quantity);
    
    /**
     * @Author AutMaple
     * @Description 批量删除购物车中的商品
     * @Date 2022/7/23 16:18
     **/
    int delete(Long memberId, List<Long> ids);
    
    /**
     * @Author AutMaple
     * @Description 获取购物车中用于选择商品规格的商品信息
     * @Date 2022/7/23 16:19
     **/
    CartProduct getCartProduct(Long productId);

    /**
     * @Author AutMaple
     * @Description 修改购物车商品的规格
     * @Date 2022/7/23 16:22
     **/
    int updateAttr(OmsCartItem cartItem);

    /**
     * @Author AutMaple
     * @Description 清空购物车
     * @Date 2022/7/23 16:23
     **/
    int clear(Long memberId);
}

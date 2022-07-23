package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.CartPromotionItem;
import com.autmaple.mall.tiny.mbg.model.OmsCartItem;

import java.util.List;

/**
 * @ClassName OmsPromotionService
 * @Description 促销管理 Service
 * @Author AutMaple
 * @Date 2022/7/23 17:01
 * @Version 1.0
 **/
public interface OmsPromotionService {
    /**
     * @Author AutMaple
     * @Description 计算购物车中的促销活动的信息
     * @Date 2022/7/23 17:02
     * @param cartItemList 购物车
     **/
    List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}

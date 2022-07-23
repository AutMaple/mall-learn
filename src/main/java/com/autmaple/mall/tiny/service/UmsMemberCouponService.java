package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.CartPromotionItem;
import com.autmaple.mall.tiny.dto.SmsCouponHistoryDetail;
import com.autmaple.mall.tiny.mbg.model.SmsCoupon;
import com.autmaple.mall.tiny.mbg.model.SmsCouponHistory;

import java.util.List;

/**
 * @ClassName UmsMemberCouponService
 * @Description 用户优惠券管理 Service
 * @Author AutMaple
 * @Date 2022/7/23 21:43
 * @Version 1.0
 **/
public interface UmsMemberCouponService {
    /**
     * @Author AutMaple
     * @Description 添加会员优惠券
     * @Date 2022/7/23 21:44
     **/
    void add(Long couponId);
    
    /**
     * @Author AutMaple
     * @Description 获取优惠券历史列表
     * @Date 2022/7/23 21:45
     **/
    List<SmsCouponHistory> listHistory(Integer useStatus);

    /**
     * @Author AutMaple
     * @Description 根据购物车信息获取可用优惠券
     * @Date 2022/7/23 21:46
     **/
    List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type);

    /**
     * @Author AutMaple
     * @Description 获取当前商品相关的优惠券
     * @Date 2022/7/23 21:47
     **/
    List<SmsCoupon> listByProduct(Long productId);

    /**
     * @Author AutMaple
     * @Description 获取用户优惠券列表
     * @Date 2022/7/23 21:47
     **/
    List<SmsCoupon> list(Integer useStatus);
}

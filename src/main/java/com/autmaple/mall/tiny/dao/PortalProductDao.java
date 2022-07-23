package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.CartProduct;
import com.autmaple.mall.tiny.dto.PromotionProduct;
import com.autmaple.mall.tiny.mbg.model.SmsCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PortalProductDao
 * @Description 前台购物车商品管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/23 16:25
 * @Version 1.0
 **/
@Mapper
public interface PortalProductDao {
    /**
     * @Author AutMaple
     * @Description 获取购物车商品信息
     * @Date 2022/7/23 16:26
     **/
    CartProduct getCartProduct(@Param("id") Long id);
    
    /**
     * @Author AutMaple
     * @Description 获取促销商品信息列表
     * @Date 2022/7/23 16:27
     **/
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 获取可用优惠券列表
     * @Date 2022/7/23 16:59
     **/
    List<SmsCoupon> getAvailableCouponList(@Param("productId") Long productId, @Param("productCategoryId") Long productCategoryId);
}

package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.SmsCoupon;
import com.autmaple.mall.tiny.mbg.model.SmsCouponHistory;
import com.autmaple.mall.tiny.mbg.model.SmsCouponProductCategoryRelation;
import com.autmaple.mall.tiny.mbg.model.SmsCouponProductRelation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName SmsCouponHistoryDetail
 * @Description
 * @Author AutMaple
 * @Date 2022/7/23 20:32
 * @Version 1.0
 **/
public class SmsCouponHistoryDetail extends SmsCouponHistory {
    @Schema(description="相关优惠券信息")
    private SmsCoupon coupon;

    @Schema(description="优惠券关联商品")
    private List<SmsCouponProductRelation> productRelationList;

    @Schema(description="优惠券关联商品分类")
    private List<SmsCouponProductCategoryRelation> categoryRelationList;

    public SmsCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(SmsCoupon coupon) {
        this.coupon = coupon;
    }

    public List<SmsCouponProductRelation> getProductRelationList() {
        return productRelationList;
    }

    public void setProductRelationList(List<SmsCouponProductRelation> productRelationList) {
        this.productRelationList = productRelationList;
    }

    public List<SmsCouponProductCategoryRelation> getCategoryRelationList() {
        return categoryRelationList;
    }

    public void setCategoryRelationList(List<SmsCouponProductCategoryRelation> categoryRelationList) {
        this.categoryRelationList = categoryRelationList;
    }
}

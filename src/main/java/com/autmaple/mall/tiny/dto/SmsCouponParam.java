package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.SmsCoupon;
import com.autmaple.mall.tiny.mbg.model.SmsCouponProductCategoryRelation;
import com.autmaple.mall.tiny.mbg.model.SmsCouponProductRelation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName SmsCouponParam
 * @Description 优惠券管理接口参数
 * @Author AutMaple
 * @Date 2022/7/17 10:00
 * @Version 1.0
 **/
public class SmsCouponParam extends SmsCoupon {
    @Schema(description="优惠券绑定的商品")
    private List<SmsCouponProductRelation> productRelationList;

    @Schema(description="优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelation> productCategoryRelationList;

    public List<SmsCouponProductRelation> getProductRelationList() {
        return productRelationList;
    }

    public void setProductRelationList(List<SmsCouponProductRelation> productRelationList) {
        this.productRelationList = productRelationList;
    }

    public List<SmsCouponProductCategoryRelation> getProductCategoryRelationList() {
        return productCategoryRelationList;
    }

    public void setProductCategoryRelationList(List<SmsCouponProductCategoryRelation> productCategoryRelationList) {
        this.productCategoryRelationList = productCategoryRelationList;
    }
}

package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionProductRelation;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName SmsFlashPromotionProduct
 * @Description 限时购商品信息封装
 * @Author AutMaple
 * @Date 2022/7/18 20:47
 * @Version 1.0
 **/
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {

    @Schema(description="限时购关联的商品")
    private PmsProduct product;

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(PmsProduct product) {
        this.product = product;
    }
}

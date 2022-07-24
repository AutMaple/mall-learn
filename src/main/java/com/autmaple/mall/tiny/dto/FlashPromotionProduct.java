package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName FlashPromotionProduct
 * @Description 秒杀信息和商品对象的封装
 * @Author AutMaple
 * @Date 2022/7/23 10:06
 * @Version 1.0
 **/
public class FlashPromotionProduct extends PmsProduct {
    @Schema(description="秒杀价格")
    private BigDecimal flashPromotionPrice;


    @Schema(description="用于秒杀的数量")
    private Integer flashPromotionCount;

    @Schema(description="秒杀限购的数量")
    private Integer flashPromotionLimit;
}

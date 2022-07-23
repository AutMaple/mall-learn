package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @ClassName FlashPromotionProduct
 * @Description 秒杀信息和商品对象的封装
 * @Author AutMaple
 * @Date 2022/7/23 10:06
 * @Version 1.0
 **/
public class FlashPromotionProduct extends PmsProduct {
    @ApiModelProperty("秒杀价格")
    private BigDecimal flashPromotionPrice;


    @ApiModelProperty("用于秒杀的数量")
    private Integer flashPromotionCount;

    @ApiModelProperty("秒杀限购的数量")
    private Integer flashPromotionLimit;
}

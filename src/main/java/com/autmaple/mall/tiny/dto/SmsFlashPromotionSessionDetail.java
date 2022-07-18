package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionSession;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName SmsFlashPromotionSessionDetail
 * @Description 包含商品数量的场次信息
 * @Author AutMaple
 * @Date 2022/7/18 21:31
 * @Version 1.0
 **/
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {

    @ApiModelProperty("商品数量")
    private Long productCount;

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}

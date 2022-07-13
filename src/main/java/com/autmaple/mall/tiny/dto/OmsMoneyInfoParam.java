package com.autmaple.mall.tiny.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @ClassName OmsMoneyInfoParam
 * @Description 修改订单费用信息参数
 * @Author AutMaple
 * @Date 2022/7/13 20:23
 * @Version 1.0
 **/
public class OmsMoneyInfoParam {
    @ApiModelProperty("订单 ID")
    private Long orderId;

    @ApiModelProperty("运输金额")
    private BigDecimal freightAmount;

    @ApiModelProperty("管理员后台调整订单所使用的折扣金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("订单状态: 0->待付款；1->待发货;2->已发货;3->已完成;4->已关闭；5->无效订单")
    private Integer status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

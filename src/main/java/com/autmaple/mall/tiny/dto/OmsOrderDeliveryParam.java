package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName OmsOrderDeliveryParam
 * @Description 订单发货参数
 * @Author AutMaple
 * @Date 2022/7/13 20:08
 * @Version 1.0
 **/
public class OmsOrderDeliveryParam {
    @Schema(description="订单 ID")
    private Long orderId;

    @Schema(description="物流公司")
    private String deliveryCompany;

    @Schema(description="物流单号")
    private String deliverySn;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }
}

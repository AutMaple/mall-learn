package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName OmsOrderQueryParam
 * @Description 订单查询参数
 * @Author AutMaple
 * @Date 2022/7/13 19:59
 * @Version 1.0
 **/
public class OmsOrderQueryParam {
    @Schema(description="订单编号")
    private String orderSn;

    @Schema(description= "收货人姓名或者号码")
    private String receiveKeyword;

    @Schema(description= "订单状态: 0->待付款; 1->待发货; 2->已发货; 3->已完成; 4->已关闭; 5->无效订单")
    private Integer status;

    @Schema(description="订单类型: 0->正常订单；1->秒杀订单")
    private Integer orderType;

    @Schema(description= "订单来源: 0->PC订单; 1->App订单")
    private Integer sourceType;

    @Schema(description= "订单提交时间")
    private String createTime;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getReceiveKeyword() {
        return receiveKeyword;
    }

    public void setReceiveKeyword(String receiveKeyword) {
        this.receiveKeyword = receiveKeyword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

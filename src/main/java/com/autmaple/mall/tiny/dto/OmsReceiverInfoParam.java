package com.autmaple.mall.tiny.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName OmsReceiverParam
 * @Description 修改订单收货人信息参数
 * @Author AutMaple
 * @Date 2022/7/13 20:16
 * @Version 1.0
 **/
public class OmsReceiverInfoParam {

    @ApiModelProperty(value = "订单 ID")
    private Long orderId;

    @ApiModelProperty("收货人姓名")
    private String receiverName;

    @ApiModelProperty("收货人电话号码")
    private String receiverPhone;

    @ApiModelProperty("收货人邮编")
    private String receiverPostCode;

    @ApiModelProperty("收货详细地址")
    private String receiverDetailAddress;

    @ApiModelProperty("省份/直辖市")
    private String receiverProvince;

    @ApiModelProperty("城市")
    private String receiverCity;

    @ApiModelProperty("区")
    private String receiverRegion;

    @ApiModelProperty("订单状态:0->待付款;1->待发货;2->已发货;3->已完成；4->已关闭;5->无效订单")
    private Integer status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }
}

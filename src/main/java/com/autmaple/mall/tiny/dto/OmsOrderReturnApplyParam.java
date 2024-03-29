package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName OmsOrderReturnApplyParam
 * @Description 退货申请请求参数
 * @Author AutMaple
 * @Date 2022/7/24 10:45
 * @Version 1.0
 **/
public class OmsOrderReturnApplyParam {
    @Schema(description="订单id")
    private Long orderId;
    @Schema(description="退货商品id")
    private Long productId;
    @Schema(description="订单编号")
    private String orderSn;
    @Schema(description="会员用户名")
    private String memberUsername;
    @Schema(description="退货人姓名")
    private String returnName;
    @Schema(description="退货人电话")
    private String returnPhone;
    @Schema(description="商品图片")
    private String productPic;
    @Schema(description="商品名称")
    private String productName;
    @Schema(description="商品品牌")
    private String productBrand;
    @Schema(description="商品销售属性：颜色：红色；尺码：xl;")
    private String productAttr;
    @Schema(description="退货数量")
    private Integer productCount;
    @Schema(description="商品单价")
    private BigDecimal productPrice;
    @Schema(description="商品实际支付单价")
    private BigDecimal productRealPrice;
    @Schema(description="原因")
    private String reason;
    @Schema(description="描述")
    private String description;
    @Schema(description="凭证图片，以逗号隔开")
    private String proofPics;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductRealPrice() {
        return productRealPrice;
    }

    public void setProductRealPrice(BigDecimal productRealPrice) {
        this.productRealPrice = productRealPrice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProofPics() {
        return proofPics;
    }

    public void setProofPics(String proofPics) {
        this.proofPics = proofPics;
    }
}

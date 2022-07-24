package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.UmsIntegrationConsumeSetting;
import com.autmaple.mall.tiny.mbg.model.UmsMemberReceiveAddress;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName ConfirmOrderResult
 * @Description 确认单消息封装
 * @Author AutMaple
 * @Date 2022/7/23 20:27
 * @Version 1.0
 **/
public class ConfirmOrderResult {
    @Schema(description="包含优惠信息的购物车信息")
    private List<CartPromotionItem> cartPromotionItemList;

    @Schema(description="用户收获地址列表")
    private List<UmsMemberReceiveAddress> memberReceiveAddressList;

    @Schema(description="用户可用优惠券列表")
    private List<SmsCouponHistoryDetail> couponHistoryDetailList;

    @Schema(description="积分使用规则")
    private UmsIntegrationConsumeSetting integrationConsumeSetting;

    @Schema(description="会员持有的积分")
    private Integer memberIntegration;

    @Schema(description="计算的金额")
    private CalcAmount calcAmount;

    public List<CartPromotionItem> getCartPromotionItemList() {
        return cartPromotionItemList;
    }

    public void setCartPromotionItemList(List<CartPromotionItem> cartPromotionItemList) {
        this.cartPromotionItemList = cartPromotionItemList;
    }

    public List<UmsMemberReceiveAddress> getMemberReceiveAddressList() {
        return memberReceiveAddressList;
    }

    public void setMemberReceiveAddressList(List<UmsMemberReceiveAddress> memberReceiveAddressList) {
        this.memberReceiveAddressList = memberReceiveAddressList;
    }

    public List<SmsCouponHistoryDetail> getCouponHistoryDetailList() {
        return couponHistoryDetailList;
    }

    public void setCouponHistoryDetailList(List<SmsCouponHistoryDetail> couponHistoryDetailList) {
        this.couponHistoryDetailList = couponHistoryDetailList;
    }

    public UmsIntegrationConsumeSetting getIntegrationConsumeSetting() {
        return integrationConsumeSetting;
    }

    public void setIntegrationConsumeSetting(UmsIntegrationConsumeSetting integrationConsumeSetting) {
        this.integrationConsumeSetting = integrationConsumeSetting;
    }

    public Integer getMemberIntegration() {
        return memberIntegration;
    }

    public void setMemberIntegration(Integer memberIntegration) {
        this.memberIntegration = memberIntegration;
    }

    public CalcAmount getCalcAmount() {
        return calcAmount;
    }

    public void setCalcAmount(CalcAmount calcAmount) {
        this.calcAmount = calcAmount;
    }

    public static class CalcAmount{
        @Schema(description="订单商品总金额")
        private BigDecimal totalAmount;

        @Schema(description="运费")
        private BigDecimal freightAmount;

        @Schema(description="活动优惠")
        private BigDecimal promotionAmount;

        @Schema(description="应付金额")
        private BigDecimal payAmount;

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }

        public BigDecimal getFreightAmount() {
            return freightAmount;
        }

        public void setFreightAmount(BigDecimal freightAmount) {
            this.freightAmount = freightAmount;
        }

        public BigDecimal getPromotionAmount() {
            return promotionAmount;
        }

        public void setPromotionAmount(BigDecimal promotionAmount) {
            this.promotionAmount = promotionAmount;
        }

        public BigDecimal getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigDecimal payAmount) {
            this.payAmount = payAmount;
        }
    }
}

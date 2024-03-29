package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName OrderParam
 * @Description 生成订单时传入的参数
 * @Author AutMaple
 * @Date 2022/6/25 11:58
 * @Version 1.0
 **/
public class OrderParam {
    @Schema(description="收获地址 ID")
    private Long memberReceiveAddressId;
    @Schema(description="优惠券 ID")
    private Long couponId;

    @Schema(description="使用的积分数")
    private Integer useIntegration;

    @Schema(description="支付方式")
    private Integer payType;

    @Schema(description="被选中的购物车商品 ID")
    private List<Long> cartIds;

    public Long getMemberReceiveAddressId() {
        return memberReceiveAddressId;
    }

    public void setMemberReceiveAddressId(Long memberReceiveAddressId) {
        this.memberReceiveAddressId = memberReceiveAddressId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Integer getUseIntegration() {
        return useIntegration;
    }

    public void setUseIntegration(Integer useIntegration) {
        this.useIntegration = useIntegration;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public List<Long> getCartIds() {
        return cartIds;
    }

    public void setCartIds(List<Long> cartIds) {
        this.cartIds = cartIds;
    }
}

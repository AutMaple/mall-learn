package com.autmaple.mall.tiny.dto;

/**
 * @ClassName OrderParam
 * @Description 生成订单时传入的参数
 * @Author AutMaple
 * @Date 2022/6/25 11:58
 * @Version 1.0
 **/
public class OrderParam {
    /**
     * @Author AutMaple
     * @Description 收货地址 ID
     * @Date 2022/6/25 12:00
     **/
    private Long memberReceiveAddressId;
    /**
     * @Author AutMaple
     * @Description 优惠券 ID
     * @Date 2022/6/25 12:00
     **/
    private Long couponId;

    /**
     * @Author AutMaple
     * @Description 使用的积分数
     * @Date 2022/6/25 12:01
     **/
    private Integer useIntegration;

    /**
     * @Author AutMaple
     * @Description 支付方式
     * @Date 2022/6/25 12:01
     **/
    private Integer payType;

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
}

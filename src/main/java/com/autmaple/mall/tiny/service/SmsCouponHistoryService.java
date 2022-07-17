package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.SmsCouponHistory;

import java.util.List;

/**
 * @ClassName SmsCouponHistoryService
 * @Description 优惠券领取记录管理 Service
 * @Author AutMaple
 * @Date 2022/7/17 13:35
 * @Version 1.0
 **/
public interface SmsCouponHistoryService {
    /**
     * @param couponId  优惠券ID
     * @param useStatus 使用状态
     * @param orderSn   使用订单号码
     * @Author AutMaple
     * @Description 分页查询优惠券领取记录
     * @Date 2022/7/17 13:37
     **/
    List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}

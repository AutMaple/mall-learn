package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.OmsOrderSetting;

/**
 * @ClassName OmsOrderSettingService
 * @Description 订单设置 Service
 * @Author AutMaple
 * @Date 2022/7/16 08:53
 * @Version 1.0
 **/
public interface OmsOrderSettingService {
    /**
     * @Author AutMaple
     * @Description 获取指定的订单设置
     * @Date 2022/7/16 08:54
     **/
    OmsOrderSetting getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 更新指定的订单设置
     * @Date 2022/7/16 08:55
     **/
    int update(Long id, OmsOrderSetting orderSetting);
}

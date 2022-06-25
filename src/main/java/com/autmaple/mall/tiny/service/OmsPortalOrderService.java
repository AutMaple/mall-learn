package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.OrderParam;

/**
 * @ClassName OmsPortalOrderService
 * @Description TODO 前台订单管理 Service
 * @Author AutMaple
 * @Date 2022/6/25 11:53
 * @Version 1.0
 **/
public interface OmsPortalOrderService {

    /**
     * @Author AutMaple
     * @Description TODO 根据提交的信息生成订单
     * @Date 2022/6/25 11:57
     **/
    CommonResult generatorOrder(OrderParam orderParam);

    /**
     * @Author AutMaple
     * @Description TODO 取消单个超时的订单
     * @Date 2022/6/25 11:57
     **/
    void cancelOrder(Long orderId);

}

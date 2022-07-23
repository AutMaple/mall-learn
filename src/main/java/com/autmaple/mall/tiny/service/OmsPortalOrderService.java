package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.dto.ConfirmOrderResult;
import com.autmaple.mall.tiny.dto.OmsOrderDetail;
import com.autmaple.mall.tiny.dto.OrderParam;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OmsPortalOrderService
 * @Description 前台订单管理 Service
 * @Author AutMaple
 * @Date 2022/6/25 11:53
 * @Version 1.0
 **/
public interface OmsPortalOrderService {
    /**
     * @Author AutMaple
     * @Description 根据用户购物车信息生成确认单信息
     * @Date 2022/7/23 20:42
     **/
    ConfirmOrderResult generateConfirmOrder(List<Long> cartIds);

    /**
     * @Author AutMaple
     * @Description 根据提交信息生成订单
     * @Date 2022/7/23 20:43
     **/
    Map<String, Object> generateOrder(OrderParam orderParam);

    /**
     * @Author AutMaple
     * @Description 支付成功后的回调
     * @Date 2022/7/23 20:43
     **/
    Integer paySuccess(Long orderId, Integer payType);

    /**
     * @Author AutMaple
     * @Description 自定取消超时的订单
     * @Date 2022/7/23 20:44
     **/
    Integer cancelTimeOutOrder();

    /**
     * @Author AutMaple
     * @Description 取消单个超市订单
     * @Date 2022/7/23 20:45
     **/
    void cancelOrder(Long orderId);
    
    /**
     * @Author AutMaple
     * @Description 发送延迟消息取消订单
     * @Date 2022/7/23 20:46
     **/
    void sendDelayMessageCancelOrder(Long orderId);
    
    /**
     * @Author AutMaple
     * @Description 确认收货
     * @Date 2022/7/23 20:47
     **/
    void confirmReceiveOrder(Long orderId);
    
    /**
     * @Author AutMaple
     * @Description 分页获取用户订单
     * @Date 2022/7/23 20:47
     **/
    CommonPage<OmsOrderDetail> list(Integer status, Integer pageNum, Integer pageSize);
    
    /**
     * @Author AutMaple
     * @Description 订单的详细信息
     * @Date 2022/7/23 20:48
     **/
    OmsOrderDetail detail(Long orderId);

    /**
     * @Author AutMaple
     * @Description 用户根据订单 ID 删除订单
     * @Date 2022/7/23 20:48
     **/
    void deleteOrder(Long orderId);
}

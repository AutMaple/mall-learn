package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.*;
import com.autmaple.mall.tiny.mbg.model.OmsOrder;

import java.util.List;

/**
 * @ClassName OmsOrderService
 * @Description 订单管理 Service
 * @Author AutMaple
 * @Date 2022/7/13 19:57
 * @Version 1.0
 **/
public interface OmsOrderService {

    /**
     * @Author AutMaple
     * @Description 订单查询
     * @Date 2022/7/13 20:07
     **/
    List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 批量发货
     * @Date 2022/7/13 20:11
     **/
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * @Author AutMaple
     * @Description 批量关闭订单
     * @Date 2022/7/13 20:11
     **/
    int close(List<Long> ids, String note);

    /**
     * @Author AutMaple
     * @Description 批量删除订单
     * @Date 2022/7/13 20:12
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 获取指定订单详细信息
     * @Date 2022/7/13 20:12
     **/
    OmsOrderDetail detail(Long id);

    /**
     * @Author AutMaple
     * @Description 修改订单收货人信息
     * @Date 2022/7/13 20:16
     **/
    int updateReceiverInfo(OmsReceiverInfoParam receiverParam);

    /**
     * @Author AutMaple
     * @Description 修改订单费用信息
     * @Date 2022/7/13 20:23
     **/
    int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);

    /**
     * @Author AutMaple
     * @Description 修改订单备注
     * @Date 2022/7/13 20:45
     **/
    int updateNote(Long id, String note, Integer status);
}

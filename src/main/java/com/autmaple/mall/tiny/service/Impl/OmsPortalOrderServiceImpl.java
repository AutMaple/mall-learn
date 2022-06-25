package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.component.CancelOrderSender;
import com.autmaple.mall.tiny.dto.OrderParam;
import com.autmaple.mall.tiny.service.OmsPortalOrderService;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OmsPortalOrderServiceImpl
 * @Description TODO 前台订单管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/6/25 12:04
 * @Version 1.0
 **/
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;


    /**
     * @Author AutMaple
     * @Description TODO 根据提交的信息生成订单
     * @Date 2022/6/25 12:06
     **/
    @Override
    public CommonResult generatorOrder(OrderParam orderParam) {
        // TODO 执行一系列下单的操作
        LOGGER.info("process generateOrder");
        // 下单完成后开启一个延迟消息，用于当用户没有付款时，取消订单( orderId 应该在下单生成)
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        // TODO 执行一系列取消订单的操作
        LOGGER.info("process cancelOrder orderId:{}", orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId){
        // 设置订单的超时时间
        long delayTimes = 30 * 1000;
        cancelOrderSender.senderMessage(orderId, delayTimes);
    }
}

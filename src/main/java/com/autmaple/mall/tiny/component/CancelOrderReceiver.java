package com.autmaple.mall.tiny.component;

import com.autmaple.mall.tiny.service.OmsPortalOrderService;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName CancelOrderReceiver
 * @Description TODO 拉取取消订单的消费者
 * @Author AutMaple
 * @Date 2022/6/25 11:50
 * @Version 1.0
 **/
@Component
public class CancelOrderReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private OmsPortalOrderService portalOrderService;

    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("receive delay message orderId: {}", orderId);
        portalOrderService.cancelOrder(orderId);
    }
}

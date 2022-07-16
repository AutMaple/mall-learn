package com.autmaple.mall.tiny.component;

import com.autmaple.mall.tiny.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName CancelOrderSender
 * @Description 生成取消订单消息的生产者
 * @Author AutMaple
 * @Date 2022/6/25 11:41
 * @Version 1.0
 **/
@Component
public class CancelOrderSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void senderMessage(Long orderId, Long delayTimes) {
        // 给延迟消息队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, message -> {
            // 给消息设置延迟时间，单位毫秒
            message.getMessageProperties()
                    .setExpiration(String.valueOf(delayTimes));
            return message;
        });

        LOGGER.info("Send delay message orderId:{}", orderId);
    }
}

package com.autmaple.mall.tiny.config;

import com.autmaple.mall.tiny.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMqConfig
 * @Description RabbitMQ 的配置文件, 用于配置交换机、队列及队列与交换机的绑定关系
 * @Author AutMaple
 * @Date 2022/6/25 10:44
 * @Version 1.0
 **/
@Configuration
public class RabbitMqConfig {
    /**
     * @Author AutMaple
     * @Description 订单消息实际消费队列锁绑定的交换机
     * @Date 2022/6/25 10:47
     **/
    @Bean
    public DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }


    /**
     * @Author AutMaple
     * @Description 订单延迟队列所绑定的交换机
     * @Date 2022/6/25 10:48
     **/
    @Bean
    public DirectExchange orderTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }


    /**
     * @Author AutMaple
     * @Description 订单实际消费队列
     * @Date 2022/6/25 10:52
     **/
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    /**
     * @Author AutMaple
     * @Description 订单延迟队列(死信队列)
     * @Date 2022/6/25 10:54
     **/
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
                .build();

    }

    /**
     * @Author AutMaple
     * @Description 将订单帮都能够到交换机
     * @Date 2022/6/25 10:56
     **/
    @Bean
    public Binding orderBinding(DirectExchange orderDirect, Queue orderQueue) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }


    /**
     * @Author AutMaple
     * @Description 将订单延迟队列绑定到交换机
     * @Date 2022/6/25 10:58
     **/
    @Bean
    public Binding orderTtlBinding(@Qualifier("orderTtlDirect") DirectExchange orderTtlExchange, Queue orderTtlQueue) {
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlExchange)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }


}

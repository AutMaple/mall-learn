package com.autmaple.mall.tiny.dto;

/**
 * @ClassName QueueEnum
 * @Description 消息队列的枚举配置
 * @Author AutMaple
 * @Date 2022/6/25 10:37
 * @Version 1.0
 **/
public enum QueueEnum {
    /**
     * @Author AutMaple
     * @Description 消息通知队列
     * @Date 2022/6/25 10:42
     **/
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),

    /**
     * @Author AutMaple
     * @Description 消息通知 TTL 队列
     * @Date 2022/6/25 10:43
     **/
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl", "mall.order.cancel.ttl", "mall.order.cancel.ttl");

    /**
     * @Author AutMaple
     * @Description 交换机名称
     * @Date 2022/6/25 10:40
     **/
    private final String exchange;

    /**
     * @Author AutMaple
     * @Description 队列名称
     * @Date 2022/6/25 10:40
     **/
    private final String name;

    /**
     * @Author AutMaple
     * @Description 路由名(key)
     * @Date 2022/6/25 10:40
     **/
    private final String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

    public String getExchange() {
        return exchange;
    }

    public String getName() {
        return name;
    }

    public String getRouteKey() {
        return routeKey;
    }
}

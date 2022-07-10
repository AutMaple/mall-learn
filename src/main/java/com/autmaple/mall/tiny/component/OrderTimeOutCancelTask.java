package com.autmaple.mall.tiny.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @ClassName OrderTimeOutCancelTask
 * @Description 订单超时取消并解锁库存的定时器
 * @Author AutMaple
 * @Date 2022/6/19 17:40
 * @Version 1.0
 **/
@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * @Author AutMaple
     * @Description 每 10 分钟扫描一次，扫描设定超时时间之前下的订单，如果没有支付就取消订单
     * @Date 2022/6/19 17:45
     **/
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder() {
        // TODO 扫描订单，并取消超时订单
        LOGGER.info("取消订单，并根据 sku 编号释放锁定的库存");
    }
}

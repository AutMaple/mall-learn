package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName OmsOrderController
 * @Description 订单管理 Controller
 * @Author AutMaple
 * @Date 2022/7/13 19:56
 * @Version 1.0
 **/
public class OmsOrderController {

    @Autowired
    private OmsOrderService orderService;
}

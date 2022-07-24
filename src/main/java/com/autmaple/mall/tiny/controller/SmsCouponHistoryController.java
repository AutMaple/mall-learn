package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.SmsCouponHistory;
import com.autmaple.mall.tiny.service.SmsCouponHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SmsCouponHistoryController
 * @Description 优惠券领取记录管理 Controller
 * @Author AutMaple
 * @Date 2022/7/17 13:34
 * @Version 1.0
 **/
@Tag(description = "SmsCouponHistoryController", name = "优惠券领取记录管理")
@RestController
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {

    @Autowired
    private SmsCouponHistoryService couponHistoryService;

    @Operation(summary="根据优惠券 ID，使用状态，订单编号分页查询领取记录")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsCouponHistory>> list(@RequestParam(value = "couponId", required = false) Long couponId,
                                                           @RequestParam(value = "useStatus", required = false) Integer useStatus,
                                                           @RequestParam(value = "orderSn", required = false) String orderSn,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsCouponHistory> couponHistoryList = couponHistoryService.list(couponId, useStatus, orderSn, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(couponHistoryList));
    }
}

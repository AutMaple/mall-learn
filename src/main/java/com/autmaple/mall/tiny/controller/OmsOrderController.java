package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.*;
import com.autmaple.mall.tiny.mbg.model.OmsOrder;
import com.autmaple.mall.tiny.service.OmsOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName OmsOrderController
 * @Description 订单管理 Controller
 * @Author AutMaple
 * @Date 2022/7/13 19:56
 * @Version 1.0
 **/
@Tag(description = "OmsOrderController", name = "订单管理")
@RestController
@RequestMapping("/order")
public class OmsOrderController {

    @Autowired
    private OmsOrderService orderService;

    @Operation(summary="查询订单")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQueryParam queryParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrder> orderList = orderService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(orderList));
    }

    @Operation(summary="批量发货")
    @PostMapping("/update/delivery")
    public CommonResult delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = orderService.delivery(deliveryParamList);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量关闭订单")
    @PostMapping("/update/close")
    public CommonResult close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = orderService.close(ids, note);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量删除订单")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = orderService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="获取订单详情：订单信息、商品信息、操作记录")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long id) {
        OmsOrderDetail orderDetailResult = orderService.detail(id);
        return CommonResult.success(orderDetailResult);
    }

    @Operation(summary="修改收货人信息")
    @PostMapping("/update/receiverInfo")
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = orderService.updateReceiverInfo(receiverInfoParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改订单费用信息")
    @PostMapping("/update/moneyInfo")
    public CommonResult updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        int count = orderService.updateMoneyInfo(moneyInfoParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="备注订单")
    @PostMapping("/update/note")
    public CommonResult updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        int count = orderService.updateNote(id, note, status);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}

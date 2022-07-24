package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.ConfirmOrderResult;
import com.autmaple.mall.tiny.dto.OmsOrderDetail;
import com.autmaple.mall.tiny.dto.OrderParam;
import com.autmaple.mall.tiny.service.OmsPortalOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OmsPortalOrderController
 * @Description 订单管理 Controller
 * @Author AutMaple
 * @Date 2022/6/25 12:13
 * @Version 1.0
 **/
@Tag(description = "OmsPortalOrderController", name = "订单管理接口")
@RestController
@RequestMapping("/portalOrder")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService portalOrderService;

    @Operation(summary = "根据购物车信息生成确认单")
    @PostMapping("/generateConfirmOrder")
    public CommonResult<ConfirmOrderResult> generateConfirmOrder(@RequestBody List<Long> cartIds) {
        ConfirmOrderResult confirmOrderResult = portalOrderService.generateConfirmOrder(cartIds);
        return CommonResult.success(confirmOrderResult);
    }

    @Operation(summary = "根据购物车信息生成订单")
    @PostMapping("/generateOrder")
    public CommonResult generateOrder(@RequestBody OrderParam orderParam) {
        Map<String, Object> result = portalOrderService.generateOrder(orderParam);
        return CommonResult.success(result, "下单成功");
    }

    @Operation(summary = "用户支付成功的回调")
    @PostMapping("/paySuccess")
    public CommonResult paySuccess(@RequestParam Long orderId, @RequestParam Integer payType) {
        Integer count = portalOrderService.paySuccess(orderId, payType);
        return CommonResult.success(count, "支付成功");
    }

    @Operation(summary = "自动取消超时订单")
    @PostMapping("/cancelTimeOutOrder")
    public CommonResult cancelTimeOutOrder() {
        portalOrderService.cancelTimeOutOrder();
        return CommonResult.success(null);
    }

    @Operation(summary = "取消单个超时订单")
    @PostMapping("/cancelOrder")
    public CommonResult cancelOrder(Long orderId) {
        portalOrderService.sendDelayMessageCancelOrder(orderId);
        return CommonResult.success(null);
    }

    //    @ApiImplicitParams(
//            @ApiImplicitParam(name = "status", value = "订单状态：-1->全部；0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭",
//                    defaultValue = "-1", allowableValues = "-1,0,1,2,3,4", paramType = "query", dataType = "int")
//    )
    @Operation(summary = "按状态分页获取用户订单列表")
    @Parameter(name = "status", description = "订单状态：-1->全部；0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrderDetail>> list(@RequestParam Integer status,
                                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        CommonPage<OmsOrderDetail> orderPage = portalOrderService.list(status, pageNum, pageSize);
        return CommonResult.success(orderPage);
    }

    @Operation(summary = "根据ID获取订单详情")
    @GetMapping("/detail/{orderId}")
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long orderId) {
        OmsOrderDetail orderDetail = portalOrderService.detail(orderId);
        return CommonResult.success(orderDetail);
    }

    @Operation(summary = "用户取消订单")
    @PostMapping("/cancelUserOrder")
    public CommonResult cancelUserOrder(Long orderId) {
        portalOrderService.cancelOrder(orderId);
        return CommonResult.success(null);
    }

    @Operation(summary = "用户确认收货")
    @PostMapping("/confirmReceiveOrder")
    public CommonResult confirmReceiveOrder(Long orderId) {
        portalOrderService.confirmReceiveOrder(orderId);
        return CommonResult.success(null);
    }

    @Operation(summary = "用户删除订单")
    @PostMapping("/deleteOrder")
    public CommonResult deleteOrder(Long orderId) {
        portalOrderService.deleteOrder(orderId);
        return CommonResult.success(null);
    }
}

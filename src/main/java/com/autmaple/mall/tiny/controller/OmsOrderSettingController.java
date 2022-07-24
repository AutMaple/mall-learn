package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.OmsOrderSetting;
import com.autmaple.mall.tiny.service.OmsOrderSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OmsOrderSettingController
 * @Description 订单设置管理 Controller
 * @Author AutMaple
 * @Date 2022/7/16 08:52
 * @Version 1.0
 **/
@Tag(description = "OmsOrderSettingController", name = "订单设置管理")
@RestController
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {

    @Autowired
    private OmsOrderSettingService settingService;

    @Operation(summary="获取指定订单设置的详细信息")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderSetting> getItem(@PathVariable Long id) {
        OmsOrderSetting orderSetting = settingService.getItem(id);
        return CommonResult.success(orderSetting);
    }

    public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderSetting orderSetting) {
        int count = settingService.update(id, orderSetting);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

}

package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.SmsCouponParam;
import com.autmaple.mall.tiny.mbg.model.SmsCoupon;
import com.autmaple.mall.tiny.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SmsCouponController
 * @Description 优惠券管理 Controller
 * @Author AutMaple
 * @Date 2022/7/17 09:58
 * @Version 1.0
 **/
@Api(tags = "SmsCouponController", description = "优惠券管理")
@RestController
@RequestMapping("/coupon")
public class SmsCouponController {
    @Autowired
    private SmsCouponService couponService;

    @ApiOperation("添加优惠券")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsCouponParam couponParam) {
        int count = couponService.create(couponParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("删除优惠券")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = couponService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("更新优惠券")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody SmsCouponParam couponParam) {
        int count = couponService.update(id, couponParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据优惠券名称或类型分页获取优惠券列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsCoupon>> list(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "type", required = false) Integer type,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsCoupon> couponList = couponService.list(name, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(couponList));
    }

    @ApiOperation("获取单个优惠券的详细信息")
    @GetMapping("/{id}")
    public CommonResult<SmsCouponParam> getItem(@PathVariable Long id) {
        SmsCouponParam couponParam = couponService.getItem(id);
        return CommonResult.success(couponParam);
    }
}

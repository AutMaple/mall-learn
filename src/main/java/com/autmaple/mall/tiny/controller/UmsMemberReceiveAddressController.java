package com.autmaple.mall.tiny.controller;


import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.UmsMemberReceiveAddress;
import com.autmaple.mall.tiny.service.UmsMemberReceiveAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName UmsMemberReceiveAddressController
 * @Description 会员收货地址管理 Controller
 * @Author AutMaple
 * @Date 2022/7/24 11:21
 * @Version 1.0
 **/
@Tag(description = "UmsMemberReceiveAddressController", name = "会员收货地址管理")
@RestController
@RequestMapping("/member/address")
public class UmsMemberReceiveAddressController {
    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;

    @Operation(summary="添加收货地址")
    @PostMapping("/add")
    public CommonResult add(@RequestBody UmsMemberReceiveAddress address) {
        int count = memberReceiveAddressService.add(address);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="删除收货地址")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = memberReceiveAddressService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改收货地址")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsMemberReceiveAddress address) {
        int count = memberReceiveAddressService.update(id, address);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="显示所有收货地址")
    @GetMapping("/list")
    public CommonResult<List<UmsMemberReceiveAddress>> list() {
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.list();
        return CommonResult.success(addressList);
    }

    @Operation(summary="获取收货地址详情")
    @GetMapping("/{id}")
    public CommonResult<UmsMemberReceiveAddress> getItem(@PathVariable Long id) {
        UmsMemberReceiveAddress address = memberReceiveAddressService.getItem(id);
        return CommonResult.success(address);
    }
}

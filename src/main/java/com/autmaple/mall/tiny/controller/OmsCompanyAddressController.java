package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.OmsCompanyAddress;
import com.autmaple.mall.tiny.service.OmsCompanyAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName OmsCompanyAddressController
 * @Description 收货地址管理 Controller
 * @Author AutMaple
 * @Date 2022/7/13 19:47
 * @Version 1.0
 **/
@Tag(description = "OOmsCompanyAddressController", name = "收货地址管理")
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {

    @Autowired
    private OmsCompanyAddressService companyAddressService;

    @Operation(summary="获取所有收获地址")
    @GetMapping("/lists")
    public CommonResult<List<OmsCompanyAddress>> list() {
        List<OmsCompanyAddress> companyAddressList = companyAddressService.list();
        return CommonResult.success(companyAddressList);
    }
}

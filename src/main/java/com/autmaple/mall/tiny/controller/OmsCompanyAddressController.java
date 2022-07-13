package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.OmsCompanyAddress;
import com.autmaple.mall.tiny.service.OmsCompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "OOmsCompanyAddressController", description = "收货地址管理")
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {

    @Autowired
    private OmsCompanyAddressService companyAddressService;

    @ApiOperation("获取所有收获地址")
    @GetMapping("/lists")
    public CommonResult<List<OmsCompanyAddress>> list() {
        List<OmsCompanyAddress> companyAddressList = companyAddressService.list();
        return CommonResult.success(companyAddressList);
    }
}

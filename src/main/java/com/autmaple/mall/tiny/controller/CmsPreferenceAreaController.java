package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.CmsPrefrenceArea;
import com.autmaple.mall.tiny.service.CmsPreferenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CmsPreferenceAreaController
 * @Description 商品优选管理 Controller
 * @Author AutMaple
 * @Date 2022/7/11 21:03
 * @Version 1.0
 **/
@Api(tags = "CmsPreferenceController", description = "商品优选管理")
@RestController
@RequestMapping("/preference")
public class CmsPreferenceAreaController {

    @Autowired
    private CmsPreferenceAreaService preferenceAreaService;


    @ApiOperation("获取所有商品优选")
    @GetMapping("/listAll")
    public CommonResult<List<CmsPrefrenceArea>> listAll() {
        List<CmsPrefrenceArea> cmsPrefrenceAreas = preferenceAreaService.listAll();
        return CommonResult.success(cmsPrefrenceAreas);
    }

}

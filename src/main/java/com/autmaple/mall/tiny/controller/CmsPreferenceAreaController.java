package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.CmsPrefrenceArea;
import com.autmaple.mall.tiny.service.CmsPreferenceAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(description = "CmsPreferenceController", name = "商品优选管理")
@RestController
@RequestMapping("/preference")
public class CmsPreferenceAreaController {

    @Autowired
    private CmsPreferenceAreaService preferenceAreaService;


    @Operation(summary = "获取所有商品优选")
    @GetMapping("/listAll")
    public CommonResult<List<CmsPrefrenceArea>> listAll() {
        List<CmsPrefrenceArea> cmsPrefrenceAreas = preferenceAreaService.listAll();
        return CommonResult.success(cmsPrefrenceAreas);
    }

}

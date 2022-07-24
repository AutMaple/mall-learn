package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.PmsBrand;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.service.PortalBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PortalBrandController
 * @Description 首页品牌推荐管理Controller
 * @Author AutMaple
 * @Date 2022/7/17 09:42
 * @Version 1.0
 **/
@Tag(description = "PortalBrandController", name = "前台品牌管理")
@RestController
@RequestMapping("/brand")
public class PortalBrandController {

    @Autowired
    private PortalBrandService homeBrandService;

    @Operation(summary="分页获取推荐品牌")
    @RequestMapping(value = "/recommendList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> recommendList(@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsBrand> brandList = homeBrandService.recommendList(pageNum, pageSize);
        return CommonResult.success(brandList);
    }

    @Operation(summary="获取品牌详情")
    @RequestMapping(value = "/detail/{brandId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> detail(@PathVariable Long brandId) {
        PmsBrand brand = homeBrandService.detail(brandId);
        return CommonResult.success(brand);
    }

    @Operation(summary="分页获取品牌相关商品")
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> productList(@RequestParam Long brandId,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        CommonPage<PmsProduct> result = homeBrandService.productList(brandId,pageNum, pageSize);
        return CommonResult.success(result);
    }
}

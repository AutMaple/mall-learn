package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.PmsBrand;
import com.autmaple.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("获取所有的品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> getAllBrand() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('pms:brand:create')")
    public CommonResult createBrand(@RequestBody PmsBrand brand){
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(brand);
        if(count == 1){
            commonResult = CommonResult.success(brand);
            LOGGER.debug("createBrand success: {}", brand);
        }else{
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed: {}", brand);
        }
        return commonResult;
    }

    @ApiOperation("根据 id 更新对应品牌的信息")
    @RequestMapping(value="/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('pms:brand:update')")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand brand){
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, brand);
        if(count == 1){
            commonResult = CommonResult.success(brand);
            LOGGER.debug("updateBrand success: {}", brand);
        }else{
            commonResult = CommonResult.failed("更新失败");
            LOGGER.debug("updateBrand failed: {}", brand);
        }
        return commonResult;
    }

    @ApiOperation("删除指定 id 对应的品牌信息")
    @RequestMapping(value="delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('pms:brand:delete')")
    public CommonResult deleteBrand(@PathVariable Long id){
        CommonResult commonResult;
        int count = pmsBrandService.deleteBrand(id);
        if(count == 1){
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteBrand success: id={}", id);
        }else{
            commonResult = CommonResult.failed("删除失败");
            LOGGER.debug("deleteBrand failed: id={}", id);
        }
        return commonResult;
    }

    @ApiOperation("分页展示品牌信息")
    @RequestMapping(value="/list", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('pms:brand:read')")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value="pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value="pageSize", defaultValue = "3") Integer pageSize){

        List<PmsBrand> brandList = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation("获取指定 id 对应品牌的详细信息")
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('pms:brand:read')")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id){
        return CommonResult.success(pmsBrandService.getBrand(id));
    }

}

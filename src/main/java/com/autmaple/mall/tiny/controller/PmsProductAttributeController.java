package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.PmsProductAttributeParam;
import com.autmaple.mall.tiny.dto.ProductAttrInfo;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttribute;
import com.autmaple.mall.tiny.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PmsProductAttributeController
 * @Description 商品属性管理 Controller
 * @Author AutMaple
 * @Date 2022/7/16 12:47
 * @Version 1.0
 **/
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或者参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0->表示属性，1->表示参数", required = true, paramType = "query", dataType = "integer")})
    @GetMapping("/list/{cid}")
    public CommonResult<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cid,
                                                                 @RequestParam(value = "type") Integer type,
                                                                 @RequestParam(value = "pageSize") Integer pageSize,
                                                                 @RequestParam(value = "pageNum") Integer pageNum) {
        List<PmsProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeList));
    }

    @ApiOperation("添加商品属性信息")
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.create(productAttributeParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改商品属性信息")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.update(id, productAttributeParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("批量删除商品属性")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = productAttributeService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据商品分类的 ID 获取所有商品属性及其属性分类")
    public CommonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return CommonResult.success(productAttrInfoList);
    }

}

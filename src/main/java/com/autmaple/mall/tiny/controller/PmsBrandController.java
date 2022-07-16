package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.PmsBrandParam;
import com.autmaple.mall.tiny.mbg.model.PmsBrand;
import com.autmaple.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);
    @Autowired
    private PmsBrandService pmsBrandService;

    @ApiOperation("获取所有的品牌列表")
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getAllBrand() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @PostMapping("/create")
    public CommonResult createBrand(@Validated @RequestBody PmsBrandParam brand) {
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(brand);
        if (count == 1) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("createBrand success: {}", brand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed: {}", brand);
        }
        return commonResult;
    }

    @ApiOperation("根据 id 更新对应品牌的信息")
    @PostMapping("/update/{id}")
    public CommonResult updateBrand(@PathVariable("id") Long id,
                                    @Validated @RequestBody PmsBrandParam brand) {
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, brand);
        if (count == 1) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("updateBrand success: {}", brand);
        } else {
            commonResult = CommonResult.failed("更新失败");
            LOGGER.debug("updateBrand failed: {}", brand);
        }
        return commonResult;
    }

    @ApiOperation("删除指定 id 对应的品牌信息")
    @GetMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable Long id) {
        CommonResult commonResult;
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteBrand success: id={}", id);
        } else {
            commonResult = CommonResult.failed("删除失败");
            LOGGER.debug("deleteBrand failed: id={}", id);
        }
        return commonResult;
    }

    @ApiOperation("分页展示品牌信息")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "keyword") String keyword,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {

        List<PmsBrand> brandList = pmsBrandService.listBrand(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation("获取指定 id 对应品牌的详细信息")
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }

    @ApiOperation("批量删除品牌")
    @PostMapping("/delete/batch")
    public CommonResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        int count = pmsBrandService.deleteBrand(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }


    @ApiOperation("批量更新显示状态")
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus) {
        int count = pmsBrandService.updateShowStatus(ids, showStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("批量更新厂家制造上状态")
    @PostMapping("/update/factoryStatus")
    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam Integer factoryStatus) {
        int count = pmsBrandService.updateFactoryStatus(ids, factoryStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }


}

package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.SmsHomeBrand;
import com.autmaple.mall.tiny.service.SmsHomeBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SmsHomeBrandController
 * @Description 首页品牌管理 Controller
 * @Author AutMaple
 * @Date 2022/7/19 20:26
 * @Version 1.0
 **/
@Api(tags = "SmsHomeBrandController", description = "首页品牌管理")
@RestController
@RequestMapping("/home/brand")
public class SmsHomeBrandController {

    @Autowired
    private SmsHomeBrandService brandService;

    @ApiOperation("添加首页品牌推荐")
    @PostMapping("/create")
    private CommonResult create(@RequestBody List<SmsHomeBrand> brandList) {
        int count = brandService.create(brandList);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改品牌推荐排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = brandService.updateSort(id, sort);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("批量删除推荐的品牌")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = brandService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam Integer recommendStatus) {
        int count = brandService.updateRecommendStatus(ids, recommendStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("分页查询推荐品牌")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeBrand>> list(@RequestParam(value = "brandName", required = false) String brandName,
                                                       @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeBrand> brandList = brandService.list(brandName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(brandList));
    }
}

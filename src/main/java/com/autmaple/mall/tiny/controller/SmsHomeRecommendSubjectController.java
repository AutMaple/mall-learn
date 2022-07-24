package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.SmsHomeRecommendSubject;
import com.autmaple.mall.tiny.service.SmsHomeRecommendSubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SmsHomeRecommendSubjectController
 * @Description 首页专题推荐管理 Controller
 * @Author AutMaple
 * @Date 2022/7/20 19:59
 * @Version 1.0
 **/
@Tag(description = "SmsHomeRecommendSubjectController", name = "首页专题推荐管理")
@RestController
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {

    @Autowired
    private SmsHomeRecommendSubjectService subjectService;

    @Operation(summary="创建首页商品主题推荐")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsHomeRecommendSubject> subjectList) {
        int count = subjectService.create(subjectList);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="修改主题推荐排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = subjectService.updateSort(id, sort);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量删除主题推荐")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = subjectService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量修改主题推荐状态")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, Integer recommendStatus) {
        int count = subjectService.updateRecommendStatus(ids, recommendStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="分页查询首页主题推荐")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> list(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRecommendSubject> subjectList = subjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(subjectList));
    }
}

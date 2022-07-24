package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.CmsSubject;
import com.autmaple.mall.tiny.service.CmsSubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CmsSubjectController
 * @Description 商品专题管理 Controller
 * @Author AutMaple
 * @Date 2022/7/11 21:17
 * @Version 1.0
 **/
@Tag(description = "CmsSubjectController", name = "商品专题管理")
@RestController
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    CmsSubjectService subjectService;

    @Operation(summary = "获取全部商品专题")
    @GetMapping("/listAll")
    public CommonResult<List<CmsSubject>> listAll() {
        List<CmsSubject> cmsSubjects = subjectService.listAll();
        return CommonResult.success(cmsSubjects);
    }

    @Operation(summary = "根据专题名称分页获取商品专题")
    @GetMapping("/list")
    public CommonResult<CommonPage<CmsSubject>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        List<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(subjectList));
    }

}

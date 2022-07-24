package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnReason;
import com.autmaple.mall.tiny.service.OmsOrderReturnReasonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName OmsOrderReturnReasonController
 * @Description 退货原因管理 Controller
 * @Author AutMaple
 * @Date 2022/7/16 08:21
 * @Version 1.0
 **/
@Tag(description = "OmsOrderReturnReasonController", name = "退货原因管理")
@RestController
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {

    @Autowired
    private OmsOrderReturnReasonService returnReasonService;

    @Operation(summary="添加退货原因")
    @PostMapping("/create")
    public CommonResult create(@RequestBody OmsOrderReturnReason returnReason) {
        int count = returnReasonService.create(returnReason);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="更新退货原因")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderReturnReason returnReason) {
        int count = returnReasonService.update(id, returnReason);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="批量删除退货原因")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = returnReasonService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="分页查询退货原因")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrderReturnReason>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnReason> returnReasonList = returnReasonService.list(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(returnReasonList));
    }

    @Operation(summary="获取退货原因的详细信息")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderReturnReason> getItem(@PathVariable Long id) {
        OmsOrderReturnReason returnReason = returnReasonService.getItem(id);
        return CommonResult.success(returnReason);
    }

    @Operation(summary="批量更新退货原因状态")
    @PostMapping("/update/status")
    public CommonResult updateStatus(@RequestParam(value = "status") Integer status,
                                     @RequestParam(value = "ids") List<Long> ids) {
        int count = returnReasonService.updateStatus(ids, status);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}

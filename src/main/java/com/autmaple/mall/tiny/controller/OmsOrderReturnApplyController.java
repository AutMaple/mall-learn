package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.OmsOrderReturnApplyResult;
import com.autmaple.mall.tiny.dto.OmsReturnApplyQueryParam;
import com.autmaple.mall.tiny.dto.OmsUpdateStatusParam;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnApply;
import com.autmaple.mall.tiny.service.OmsOrderReturnApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName OmsOrderReturnApplyController
 * @Description 订单退货申请管理 Controller
 * @Author AutMaple
 * @Date 2022/7/15 21:14
 * @Version 1.0
 **/
@Tag(description = "OmsOrderReturnApplyController", name="订单退货申请管理")
@RestController
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {

    @Autowired
    private OmsOrderReturnApplyService returnApplyService;

    @Operation(summary="分页查询订单申请")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrderReturnApply>> list(OmsReturnApplyQueryParam queryParam,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnApply> returnApplyList = returnApplyService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(returnApplyList));
    }


    @Operation(summary="批量删除退货申请")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = returnApplyService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary="获取退货申请的详细信息")
    @GetMapping("/{id}")
    public CommonResult getItem(@PathVariable Long id) {
        OmsOrderReturnApplyResult result = returnApplyService.getItem(id);
        return CommonResult.success(result);
    }

    @Operation(summary="修改退货申请状态")
    @PostMapping("/update/status/{id}")
    public CommonResult updateState(@PathVariable Long id, @RequestBody OmsUpdateStatusParam statusParam) {
        int count = returnApplyService.updateStatus(id, statusParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}

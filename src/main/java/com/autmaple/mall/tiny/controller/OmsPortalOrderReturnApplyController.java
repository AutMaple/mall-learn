package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.OmsOrderReturnApplyParam;
import com.autmaple.mall.tiny.service.OmsPortalOrderReturnApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OmsPortalOrderReturnApplyController
 * @Description 退货申请管理 Controller
 * @Author AutMaple
 * @Date 2022/7/24 10:42
 * @Version 1.0
 **/
@Tag(description = "OmsPortalOrderReturnApplyController", name = "退货申请管理")
@RestController
@RequestMapping("/returnApply")
public class OmsPortalOrderReturnApplyController {
    @Autowired
    private OmsPortalOrderReturnApplyService returnApplyService;

    @Operation(summary="申请退货")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody OmsOrderReturnApplyParam returnApply) {
        int count = returnApplyService.create(returnApply);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}

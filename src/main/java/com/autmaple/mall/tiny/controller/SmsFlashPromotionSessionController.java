package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.SmsFlashPromotionSessionDetail;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionSession;
import com.autmaple.mall.tiny.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionSessionController
 * @Description 限时购场次管理 Controller
 * @Author AutMaple
 * @Date 2022/7/18 21:20
 * @Version 1.0
 **/
@Api(tags = "SmsFlashPromotionSessionController", description = "限时购场次管理")
@RestController
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {

    @Autowired
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("添加场次")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsFlashPromotionSession session) {
        int count = flashPromotionSessionService.create(session);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("更新场次")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody SmsFlashPromotionSession session) {
        int count = flashPromotionSessionService.update(id, session);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改启用状态")
    @PostMapping("/update/status/{id}")
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        int count = flashPromotionSessionService.updateStatus(id, status);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("删除场次")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = flashPromotionSessionService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("获取场次的详细信息")
    @GetMapping("/{id}")
    public CommonResult<SmsFlashPromotionSession> getItem(@PathVariable Long id) {
        SmsFlashPromotionSession item = flashPromotionSessionService.getItem(id);
        return CommonResult.success(item);
    }

    @ApiOperation("获取全部场次")
    @GetMapping("/list")
    public CommonResult<List<SmsFlashPromotionSession>> list() {
        List<SmsFlashPromotionSession> sessionList = flashPromotionSessionService.list();
        return CommonResult.success(sessionList);
    }

    @ApiOperation("获取全部可选场次及其数量")
    @GetMapping("/selectList")
    public CommonResult<List<SmsFlashPromotionSessionDetail>> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> detailsList = flashPromotionSessionService.selectList(flashPromotionId);
        return CommonResult.success(detailsList);
    }
}

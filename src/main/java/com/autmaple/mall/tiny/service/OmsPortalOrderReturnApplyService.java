package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.OmsOrderReturnApplyParam;

/**
 * @ClassName OmsPortalOrderReturnApplyService
 * @Description 退货申请管理 Service
 * @Author AutMaple
 * @Date 2022/7/24 10:44
 * @Version 1.0
 **/
public interface OmsPortalOrderReturnApplyService {
    /**
     * @Author AutMaple
     * @Description 提交申请
     * @Date 2022/7/24 10:45
     **/
    int create(OmsOrderReturnApplyParam returnApply);
}

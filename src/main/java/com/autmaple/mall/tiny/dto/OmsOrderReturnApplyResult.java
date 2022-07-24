package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.OmsCompanyAddress;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName OmsOrderReturnApplyResult
 * @Description 退货申请信息封装
 * @Author AutMaple
 * @Date 2022/7/15 21:32
 * @Version 1.0
 **/
public class OmsOrderReturnApplyResult {
    @Schema(description="公司收货地址")
    private OmsCompanyAddress companyAddress;

    public OmsCompanyAddress getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(OmsCompanyAddress companyAddress) {
        this.companyAddress = companyAddress;
    }
}

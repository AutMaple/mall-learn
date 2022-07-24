package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName OmsUpdateStatusParam
 * @Description 确认收获请求参数
 * @Author AutMaple
 * @Date 2022/7/15 21:26
 * @Version 1.0
 **/
public class OmsUpdateStatusParam {
    @Schema(description="服务单号")
    private Long id;

    @Schema(description="收货地址关联 ID")
    private Long companyAddressId;

    @Schema(description="确认退款金额")
    private BigDecimal returnAmount;

    @Schema(description="处理备注")
    private String handleNote;

    @Schema(description="处理人")
    private String handleMan;

    @Schema(description="收货备注")
    private String receiveNote;

    @Schema(description="收货人")
    private String receiveMan;

    @Schema(description="申请状态： 1->退货中；2->已完成；3->已拒绝")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(Long companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getHandleMan() {
        return handleMan;
    }

    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan;
    }

    public String getReceiveNote() {
        return receiveNote;
    }

    public void setReceiveNote(String receiveNote) {
        this.receiveNote = receiveNote;
    }

    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

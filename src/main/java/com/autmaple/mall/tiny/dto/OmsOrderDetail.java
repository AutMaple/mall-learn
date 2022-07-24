package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.OmsOrder;
import com.autmaple.mall.tiny.mbg.model.OmsOrderItem;
import com.autmaple.mall.tiny.mbg.model.OmsOrderOperateHistory;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName OmsOrderDetail
 * @Description 订单详细信息
 * @Author AutMaple
 * @Date 2022/7/13 20:13
 * @Version 1.0
 **/
public class OmsOrderDetail extends OmsOrder {
    @Schema(description="订单商品列表")
    private List<OmsOrderItem> orderItemList;

    @Schema(description="订单操作记录列表")
    private List<OmsOrderOperateHistory> historyList;

    public List<OmsOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OmsOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<OmsOrderOperateHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<OmsOrderOperateHistory> historyList) {
        this.historyList = historyList;
    }
}

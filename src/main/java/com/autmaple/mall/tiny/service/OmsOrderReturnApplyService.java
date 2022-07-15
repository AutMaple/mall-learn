package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.OmsOrderReturnApplyResult;
import com.autmaple.mall.tiny.dto.OmsReturnApplyQueryParam;
import com.autmaple.mall.tiny.dto.OmsUpdateStatusParam;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnApply;

import java.util.List;

/**
 * @ClassName OmsOrderReturnApplyService
 * @Description 订单申请退货管理 Service
 * @Author AutMaple
 * @Date 2022/7/15 21:23
 * @Version 1.0
 **/
public interface OmsOrderReturnApplyService {
    /**
     * @Author AutMaple
     * @Description 分页查询订单退货申请
     * @Date 2022/7/15 21:25
     **/
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 批量删除订单退货申请
     * @Date 2022/7/15 21:25
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 修改指定申请状态
     * @Date 2022/7/15 21:26
     **/
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

    /**
     * @Author AutMaple
     * @Description 获取指定退货申请的详细情况
     * @Date 2022/7/15 21:31
     **/
    OmsOrderReturnApplyResult getItem(Long id);
}

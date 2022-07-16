package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnReason;

import java.util.List;

/**
 * @ClassName OmsOrderReturnReasonService
 * @Description 退货原因管理 Service
 * @Author AutMaple
 * @Date 2022/7/16 08:22
 * @Version 1.0
 **/
public interface OmsOrderReturnReasonService {
    /**
     * @Author AutMaple
     * @Description 添加退货通知
     * @Date 2022/7/16 08:24
     **/
    int create(OmsOrderReturnReason returnReason);

    /**
     * @Author AutMaple
     * @Description 更新退货原因
     * @Date 2022/7/16 08:24
     **/
    int update(Long id, OmsOrderReturnReason returnReason);

    /**
     * @Author AutMaple
     * @Description 批量删除退货原因
     * @Date 2022/7/16 08:25
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 分页获取退货原因
     * @Date 2022/7/16 08:25
     **/
    List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 批量修改退货原因的状态
     * @Date 2022/7/16 08:26
     **/
    int updateStatus(List<Long> ids, Integer status);

    /**
     * @Author AutMaple
     * @Description 获取指定退货原因的详细信息
     * @Date 2022/7/16 08:27
     **/
    OmsOrderReturnReason getItem(Long id);
}

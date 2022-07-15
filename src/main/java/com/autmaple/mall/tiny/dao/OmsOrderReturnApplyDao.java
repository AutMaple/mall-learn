package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.OmsOrderReturnApplyResult;
import com.autmaple.mall.tiny.dto.OmsReturnApplyQueryParam;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName OmsOrderReturnApplyDao
 * @Description 订单推过申请自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/15 21:34
 * @Version 1.0
 **/
public interface OmsOrderReturnApplyDao {
    /**
     * @Author AutMaple
     * @Description 查询申请列表
     * @Date 2022/7/15 21:36
     **/
    List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParam queryParam);

    /**
     * @Author AutMaple
     * @Description 获取退货申请的详细信息
     * @Date 2022/7/15 21:37
     **/
    OmsOrderReturnApplyResult getDetail(@Param("id") Long id);
}

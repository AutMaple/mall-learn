package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.OmsOrderDeliveryParam;
import com.autmaple.mall.tiny.dto.OmsOrderDetail;
import com.autmaple.mall.tiny.dto.OmsOrderQueryParam;
import com.autmaple.mall.tiny.mbg.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName OmsOrderDao
 * @Description 订单管理 Dao
 * @Author AutMaple
 * @Date 2022/7/13 20:47
 * @Version 1.0
 **/
public interface OmsOrderDao {
    /**
     * @Author AutMaple
     * @Description 调价查询订单
     * @Date 2022/7/13 20:49
     **/
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * @Author AutMaple
     * @Description 批量发货
     * @Date 2022/7/13 20:52
     **/
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * @Author AutMaple
     * @Description 获取订单详情
     * @Date 2022/7/13 20:53
     **/
    OmsOrderDetail getDetail(@Param("id") Long id);
}

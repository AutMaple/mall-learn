package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.OmsOrderDetail;
import com.autmaple.mall.tiny.mbg.model.OmsOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PortalOrderDao
 * @Description 前台订单管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/23 22:16
 * @Version 1.0
 **/
@Mapper
public interface PortalOrderDao {
    /**
     * @Author AutMaple
     * @Description 获取订单及下单商品详情
     * @Date 2022/7/23 22:18
     **/
    OmsOrderDetail getDetail(@Param("orderId") Long orderId);

    /**
     * @Author AutMaple
     * @Description 修改 pms_sku_stock表的锁定库存及真实库存
     * @Date 2022/7/23 22:18
     **/
    int updateSkuStock(@Param("itemList") List<OmsOrderItem> orderItemList);

    /**
     * @Author AutMaple
     * @Description 获取超时订单
     * @Date 2022/7/23 22:18
     * @param minute 超时时间
     **/
    List<OmsOrderDetail> getTimeOutOrders(@Param("minute") Integer minute);

    /**
     * @Author AutMaple
     * @Description 批量修改订单状态
     * @Date 2022/7/23 22:19
     **/
    int updateOrderStatus(@Param("ids") List<Long> ids,@Param("status") Integer status);

    /**
     * @Author AutMaple
     * @Description 解除取消订单的库存锁定
     * @Date 2022/7/23 22:19
     **/
    int releaseSkuStockLock(@Param("itemList") List<OmsOrderItem> orderItemList);
}

package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.OmsOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PortalOrderItemDao
 * @Description 订单商品信息管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/23 22:13
 * @Version 1.0
 **/
@Mapper
public interface PortalOrderItemDao {


    /**
     * @Author AutMaple
     * @Description 批量插入
     * @Date 2022/7/23 22:14
     **/
    int insertList(@Param("list")List<OmsOrderItem> list);

}

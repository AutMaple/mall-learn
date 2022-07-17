package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.SmsCouponParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName SmsCouponDao
 * @Description 优惠券管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/17 10:15
 * @Version 1.0
 **/
@Mapper
public interface SmsCouponDao {
    /**
     * @Author AutMaple
     * @Description 获取优惠券的详细信息包括绑定关系
     * @Date 2022/7/17 10:16
     **/
    SmsCouponParam getItem(@Param("id") Long id);
}

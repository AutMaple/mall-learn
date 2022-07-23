package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.SmsCouponHistoryDetail;
import com.autmaple.mall.tiny.mbg.model.SmsCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SmsCouponHistoryDao
 * @Description
 * @Author AutMaple
 * @Date 2022/7/23 21:49
 * @Version 1.0
 **/
@Mapper
public interface SmsCouponHistoryDao {
    /**
     * @Author AutMaple
     * @Description 获取优惠券历史详情
     * @Date 2022/7/23 21:50
     **/
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);


    /**
     * @Author AutMaple
     * @Description 获取指定会员优惠券列表
     * @Date 2022/7/23 21:51
     **/
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId,@Param("useStatus")Integer useStatus);
}

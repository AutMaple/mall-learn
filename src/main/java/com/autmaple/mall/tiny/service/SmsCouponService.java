package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.SmsCouponParam;
import com.autmaple.mall.tiny.mbg.model.SmsCoupon;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import java.util.List;

/**
 * @ClassName SmsCouponService
 * @Description 优惠券管理 Service
 * @Author AutMaple
 * @Date 2022/7/17 09:59
 * @Version 1.0
 **/
public interface SmsCouponService {
    /**
     * @Author AutMaple
     * @Description 添加优惠券
     * @Date 2022/7/17 10:02
     **/
    int create(SmsCouponParam couponParam);

    /**
     * @Author AutMaple
     * @Description 根据 ID 删除优惠券
     * @Date 2022/7/17 10:03
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 根据优惠券 ID 更新优惠券信息
     * @Date 2022/7/17 10:03
     **/
    int update(Long id, SmsCouponParam couponParam);

    /**
     * @Author AutMaple
     * @Description 分页获取优惠券列表
     * @Date 2022/7/17 10:04
     **/
    List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 获取优惠券的详细信息
     * @Date 2022/7/17 10:05
     **/
    SmsCouponParam getItem(Long id);
}

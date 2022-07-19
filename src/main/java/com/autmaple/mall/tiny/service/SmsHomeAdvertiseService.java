package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.SmsHomeAdvertise;

import java.util.List;

/**
 * @ClassName SmsHomeAdvertiseService
 * @Description 首页轮播广告管理 Service
 * @Author AutMaple
 * @Date 2022/7/19 19:56
 * @Version 1.0
 **/
public interface SmsHomeAdvertiseService {
    /**
     * @Author AutMaple
     * @Description 创建广告
     * @Date 2022/7/19 19:58
     **/
    int create(SmsHomeAdvertise advertise);

    /**
     * @Author AutMaple
     * @Description 批量删除广告
     * @Date 2022/7/19 19:59
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 修改广告上下线的状态
     * @Date 2022/7/19 19:59
     **/
    int updateStatus(Long id, Integer status);

    /**
     * @Author AutMaple
     * @Description 获取广告的详细信息
     * @Date 2022/7/19 20:00
     **/
    SmsHomeAdvertise getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 修改广告的信息
     * @Date 2022/7/19 20:01
     **/
    int update(Long id, SmsHomeAdvertise advertise);

    /**
     * @Author AutMaple
     * @Description 分页查询广告
     * @Date 2022/7/19 20:02
     **/
    List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);
}

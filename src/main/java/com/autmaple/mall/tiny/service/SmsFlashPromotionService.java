package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotion;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionService
 * @Description 限时购活动管理 Service
 * @Author AutMaple
 * @Date 2022/7/17 13:50
 * @Version 1.0
 **/
public interface SmsFlashPromotionService {
    /**
     * @Author AutMaple
     * @Description 添加活动
     * @Date 2022/7/17 13:51
     **/
    int create(SmsFlashPromotion flashPromotion);

    /**
     * @Author AutMaple
     * @Description 修改指定限时购活动
     * @Date 2022/7/17 13:52
     **/
    int update(Long id, SmsFlashPromotion flashPromotion);

    /**
     * @Author AutMaple
     * @Description 删除某个限时购活动
     * @Date 2022/7/17 13:52
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 修改限时购的上下线时间
     * @Date 2022/7/17 13:53
     **/
    int updateStatus(Long id, Integer status);

    /**
     * @Author AutMaple
     * @Description 获取限时购活动详情
     * @Date 2022/7/17 13:53
     **/
    SmsFlashPromotion getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 分页查询限时购活动
     * @Date 2022/7/17 13:54
     **/
    List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum);
}

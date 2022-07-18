package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.SmsFlashPromotionProduct;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionProductRelation;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionProductRelationService
 * @Description 限时购活动和商品关系管理 Service
 * @Author AutMaple
 * @Date 2022/7/18 20:39
 * @Version 1.0
 **/
public interface SmsFlashPromotionProductRelationService {
    /**
     * @Author AutMaple
     * @Description 批量创建限时购活动和商品的关系
     * @Date 2022/7/18 20:40
     **/
    int create(List<SmsFlashPromotionProductRelation> relationList);

    /**
     * @Author AutMaple
     * @Description 修改限时购活动和商品的关系
     * @Date 2022/7/18 20:41
     **/
    int update(Long id, SmsFlashPromotionProductRelation relation);

    /**
     * @Author AutMaple
     * @Description 删除限时购活动和商品的关系
     * @Date 2022/7/18 20:41
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 获取关联的详细信息
     * @Date 2022/7/18 20:42
     **/
    SmsFlashPromotionProductRelation getItem(Long id);

    /**
     * @param flashPromotionId        限时购 ID
     * @param flashPromotionSessionId 限时购场次 ID
     * @Author AutMaple
     * @Description 分页查询相关商品及限时购促销信息
     * @Date 2022/7/18 20:44
     **/
    List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);

    /**
     * @param flashPromotionId        限时购 ID
     * @param flashPromotionSessionId 限时购场次
     * @Author AutMaple
     * @Description 根据活动和场次 ID 获取商品关系书香
     * @Date 2022/7/18 20:50
     **/
    Long getCount(Long flashPromotionId, Long flashPromotionSessionId);
}

package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.SmsFlashPromotionProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionProductRelationDao
 * @Description 限时购活动和商品关系自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/18 20:53
 * @Version 1.0
 **/
@Mapper
public interface SmsFlashPromotionProductRelationDao {
    /**
     * @Author AutMaple
     * @Description 获取限时购及相关商品信息
     * @Date 2022/7/18 20:54
     **/
    List<SmsFlashPromotionProduct> getList(@Param("flashPromotionId") Long flashPromotionId,
                                           @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}

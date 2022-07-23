package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.FlashPromotionProduct;
import com.autmaple.mall.tiny.mbg.model.CmsSubject;
import com.autmaple.mall.tiny.mbg.model.PmsBrand;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName HomeDao
 * @Description 首页内容管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/23 10:18
 * @Version 1.0
 **/
@Mapper
public interface HomeDao {
    /**
     * @Author AutMaple
     * @Description 获取推荐品牌
     * @Date 2022/7/23 10:20
     **/
    List<PmsBrand> getRecommendBrandList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * @Author AutMaple
     * @Description 获取秒杀商品
     * @Date 2022/7/23 10:21
     **/
    List<FlashPromotionProduct> getFlashProductList(@Param("flashPromotionId") Long flashPromotionId, @Param("sessionId") Long sessionId);

    /**
     * @Author AutMaple
     * @Description 获取新品推荐
     * @Date 2022/7/23 10:26
     **/
    List<PmsProduct> getNewProductList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * @Author AutMaple
     * @Description 获取人气推荐
     * @Date 2022/7/23 10:27
     **/
    List<PmsProduct> getHotProductList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * @Author AutMaple
     * @Description 获取推荐专题
     * @Date 2022/7/23 10:28
     **/
    List<CmsSubject> getRecommendSubjectList(@Param("offset") Integer offset, @Param("limit")Integer limit);
}

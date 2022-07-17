package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.SmsCouponProductCategoryRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SmsCouponProductCategoryRelationDao
 * @Description 优惠券与商品分类关系管理 Dao
 * @Author AutMaple
 * @Date 2022/7/17 10:33
 * @Version 1.0
 **/
@Mapper
public interface SmsCouponProductCategoryRelationDao {
    /**
     * @Author AutMaple
     * @Description 批量插入优惠券与商品分类关系
     * @Date 2022/7/17 10:34
     **/
    int insertList(@Param("list") List<SmsCouponProductCategoryRelation> productCategoryRelationList);
}

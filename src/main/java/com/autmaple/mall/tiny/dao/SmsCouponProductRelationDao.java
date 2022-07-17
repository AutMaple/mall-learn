package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.SmsCouponProductRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SmsCouponProductRelationDao
 * @Description 优惠券与商品关系的自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/17 10:07
 * @Version 1.0
 **/
@Mapper
public interface SmsCouponProductRelationDao {
    /**
     * @Author AutMaple
     * @Description 批量创建优惠券与商品之间的关系
     * @Date 2022/7/17 10:11
     **/
    int insertList(@Param("list")List<SmsCouponProductRelation> productRelationList);
}

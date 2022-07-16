package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PmsProductCategoryAttributeRelationDao
 * @Description 商品分类与属性关系 Dao
 * @Author AutMaple
 * @Date 2022/7/16 17:14
 * @Version 1.0
 **/
@Mapper
public interface PmsProductCategoryAttributeRelationDao {
    /**
     * @Author AutMaple
     * @Description 批量创建
     * @Date 2022/7/16 17:16
     **/
    int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList);
}

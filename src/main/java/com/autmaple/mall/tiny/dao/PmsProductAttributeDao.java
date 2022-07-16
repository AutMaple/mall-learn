package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.ProductAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PmsProductAttributeDao
 * @Description 商品属性管理 Dao
 * @Author AutMaple
 * @Date 2022/7/16 13:01
 * @Version 1.0
 **/
public interface PmsProductAttributeDao {
    /**
     * @Author AutMaple
     * @Description 获取商品属性信息
     * @Date 2022/7/16 13:03
     **/
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);

}

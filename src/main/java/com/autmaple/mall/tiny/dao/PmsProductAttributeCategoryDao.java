package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.PmsProductAttributeCategoryItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName PmsProductAttributeCategoryDao
 * @Description 商品属性分类 Dao
 * @Author AutMaple
 * @Date 2022/7/16 12:22
 * @Version 1.0
 **/
@Mapper
public interface PmsProductAttributeCategoryDao {

    /**
     * @Author AutMaple
     * @Description 获取包含属性的商品分类
     * @Date 2022/7/16 12:23
     **/
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}

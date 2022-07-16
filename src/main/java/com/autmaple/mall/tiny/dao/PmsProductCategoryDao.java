package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.PmsProductCategoryWithChildrenItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName PmsProductCategoryDao
 * @Description 商品分类管理 Dao
 * @Author AutMaple
 * @Date 2022/7/16 17:53
 * @Version 1.0
 **/
@Mapper
public interface PmsProductCategoryDao {
    /**
     * @Author AutMaple
     * @Description 获取商品分类及其子类
     * @Date 2022/7/16 17:55
     **/
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}

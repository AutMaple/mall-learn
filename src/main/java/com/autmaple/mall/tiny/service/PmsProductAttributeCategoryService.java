package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.PmsProductAttributeCategoryItem;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * @ClassName PmsProductAttributeCategoryService
 * @Description 商品属性分类管理 Service
 * @Author AutMaple
 * @Date 2022/7/16 12:14
 * @Version 1.0
 **/
public interface PmsProductAttributeCategoryService {
    /**
     * @Author AutMaple
     * @Description 创建属性分类
     * @Date 2022/7/16 12:15
     **/
    int create(String name);

    /**
     * @Author AutMaple
     * @Description 修改属性分类
     * @Date 2022/7/16 12:15
     **/
    int update(Long id, String name);

    /**
     * @Author AutMaple
     * @Description 删除属性分类
     * @Date 2022/7/16 12:16
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 获取指定商品属性的详细信息
     * @Date 2022/7/16 12:16
     **/
    PmsProductAttributeCategory getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 分页查询商品属性分类信息
     * @Date 2022/7/16 12:17
     **/
    List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    List<PmsProductAttributeCategoryItem> getListWithAttr();
}


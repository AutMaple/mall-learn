package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.PmsProductCategoryParam;
import com.autmaple.mall.tiny.dto.PmsProductCategoryWithChildrenItem;
import com.autmaple.mall.tiny.mbg.model.PmsProductCategory;

import java.util.List;

/**
 * @ClassName PmsProductCategoryService
 * @Description 商品分类管理 Service
 * @Author AutMaple
 * @Date 2022/7/16 16:53
 * @Version 1.0
 **/
public interface PmsProductCategoryService {
    /**
     * @Author AutMaple
     * @Description 创建商品分类
     * @Date 2022/7/16 16:55
     **/
    int create(PmsProductCategoryParam productCategoryParam);

    /**
     * @Author AutMaple
     * @Description 修改商品分类
     * @Date 2022/7/16 17:04
     **/
    int update(Long id, PmsProductCategoryParam productCategoryParam);

    /**
     * @Author AutMaple
     * @Description 分页获取商品分类
     * @Date 2022/7/16 17:05
     **/
    List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 删除商品分类
     * @Date 2022/7/16 17:05
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 获取指定商品分类的详细信息
     * @Date 2022/7/16 17:06
     **/
    PmsProductCategory getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 批量修改导航状态
     * @Date 2022/7/16 17:07
     **/
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * @Author AutMaple
     * @Description 批量修改展示状态
     * @Date 2022/7/16 17:48
     **/
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * @Author AutMaple
     * @Description 以层级形式获取商品分类
     * @Date 2022/7/16 17:08
     **/
    List<PmsProductCategoryWithChildrenItem> listWithChildren();


}

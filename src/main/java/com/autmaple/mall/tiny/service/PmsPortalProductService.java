package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.PmsPortalProductDetail;
import com.autmaple.mall.tiny.dto.PmsProductCategoryNode;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;

import java.util.List;

/**
 * @ClassName PmsPortalProductService
 * @Description 前台商品管理 Service
 * @Author AutMaple
 * @Date 2022/7/24 10:52
 * @Version 1.0
 **/
public interface PmsPortalProductService {
    /**
     * @Author AutMaple
     * @Description 综合搜索商品
     * @Date 2022/7/24 10:53
     **/
    List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

    /**
     * @Author AutMaple
     * @Description 以树形结构获取所有商品分类
     * @Date 2022/7/24 10:53
     **/
    List<PmsProductCategoryNode> categoryTreeList();

    /**
     * @Author AutMaple
     * @Description 获取前台商品详情
     * @Date 2022/7/24 10:54
     **/
    PmsPortalProductDetail detail(Long id);
}

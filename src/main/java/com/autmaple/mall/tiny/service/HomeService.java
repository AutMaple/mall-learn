package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.HomeContentResult;
import com.autmaple.mall.tiny.mbg.model.CmsSubject;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.mbg.model.PmsProductCategory;

import java.util.List;

/**
 * @ClassName HomeService
 * @Description 首页内容管理 Service
 * @Author AutMaple
 * @Date 2022/7/23 09:59
 * @Version 1.0
 **/
public interface HomeService {

    /**
     * @Author AutMaple
     * @Description 获取首页内容
     * @Date 2022/7/23 10:12
     **/
    HomeContentResult content();

    /**
     * @Author AutMaple
     * @Description 首页商品推荐
     * @Date 2022/7/23 10:13
     **/
    List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum);

    /**
     * @param parentId 0:获取以及分类; 其他:获取指定二级分类
     * @Author AutMaple
     * @Description 获取商品的分类
     * @Date 2022/7/23 10:13
     **/
    List<PmsProductCategory> getProductCategoryList(Long parentId);


    /**
     * @Author AutMaple
     * @Description 根据专题分类分页获取专题
     * @Date 2022/7/23 10:15
     **/
    List<CmsSubject> getSubjectList(Long categoryId, Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 分页获取人气推荐商品
     * @Date 2022/7/23 10:16
     **/
    List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize);

    /**
     * @Author AutMaple
     * @Description 分页获取新品推荐商品
     * @Date 2022/7/23 10:16
     **/
    List<PmsProduct> newProductList(Integer pageNum, Integer pageSize);
}

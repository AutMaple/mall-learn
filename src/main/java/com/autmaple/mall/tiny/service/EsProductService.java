package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.EsProductRelatedInfo;
import com.autmaple.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName EsProductService
 * @Description 商品搜索管理 Service
 * @Author AutMaple
 * @Date 2022/6/19 20:20
 * @Version 1.0
 **/
public interface EsProductService {
    /**
     * @Author AutMaple
     * @Description 从数据库中导入所有商品到ES
     * @Date 2022/7/24 11:26
     **/
    int importAll();

    /**
     * @Author AutMaple
     * @Description 根据id删除商品
     * @Date 2022/7/24 11:27
     **/
    void delete(Long id);

    /**
     * @Author AutMaple
     * @Description 根据id创建商品
     * @Date 2022/7/24 11:27
     **/
    EsProduct create(Long id);

    /**
     * @Author AutMaple
     * @Description 批量删除商品
     * @Date 2022/7/24 11:27
     **/
    void delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 根据关键字搜索名称或者副标题
     * @Date 2022/7/24 11:28
     **/
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

    /**
     * @Author AutMaple
     * @Description 根据关键字搜索名称或者副标题复合查询
     * @Date 2022/7/24 11:28
     **/
    Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort);

    /**
     * @Author AutMaple
     * @Description 根据商品id推荐相关商品
     * @Date 2022/7/24 11:28
     **/
    Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize);

    /**
     * @Author AutMaple
     * @Description 获取搜索词相关品牌、分类、属性
     * @Date 2022/7/24 11:28
     **/
    EsProductRelatedInfo searchRelatedInfo(String keyword);
}

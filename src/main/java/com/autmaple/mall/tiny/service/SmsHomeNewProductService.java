package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.SmsHomeNewProduct;

import java.util.List;

/**
 * @ClassName SmsHomeNewProductService
 * @Description 首页新品管理 Service
 * @Author AutMaple
 * @Date 2022/7/19 20:56
 * @Version 1.0
 **/
public interface SmsHomeNewProductService {
    /**
     * @Author AutMaple
     * @Description 添加新品推荐
     * @Date 2022/7/19 20:58
     **/
    int create(List<SmsHomeNewProduct> homeNewProductList);

    /**
     * @Author AutMaple
     * @Description 修改新品的排序
     * @Date 2022/7/19 20:58
     **/
    int updateSort(Long id, Integer sort);

    /**
     * @Author AutMaple
     * @Description 批量删除推荐
     * @Date 2022/7/19 20:59
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 批量更新推荐状态
     * @Date 2022/7/19 20:59
     **/
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * @Author AutMaple
     * @Description 分页查询推荐
     * @Date 2022/7/19 21:00
     **/
    List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}

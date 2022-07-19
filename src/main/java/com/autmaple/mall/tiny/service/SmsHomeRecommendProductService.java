package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.SmsHomeRecommendProduct;

import java.util.List;

/**
 * @ClassName SmsHomeRecommendProductService
 * @Description 首页人气推荐管理 Service
 * @Author AutMaple
 * @Date 2022/7/19 21:23
 * @Version 1.0
 **/
public interface SmsHomeRecommendProductService {
    /**
     * @Author AutMaple
     * @Description 添加首页推荐
     * @Date 2022/7/19 21:25
     **/
    int create(List<SmsHomeRecommendProduct> productList);

    /**
     * @Author AutMaple
     * @Description 修改推荐排序
     * @Date 2022/7/19 21:26
     **/
    int updateSort(Long id, Integer sort);

    /**
     * @Author AutMaple
     * @Description 批量删除推荐
     * @Date 2022/7/19 21:26
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 批量更新推荐状态
     * @Date 2022/7/19 21:27
     **/
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * @Author AutMaple
     * @Description 分页查询推荐商品
     * @Date 2022/7/19 21:28
     **/
    List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}

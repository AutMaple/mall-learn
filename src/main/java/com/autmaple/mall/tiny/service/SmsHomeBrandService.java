package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.SmsHomeBrand;

import java.util.List;

/**
 * @ClassName SmsHomeBrandService
 * @Description 首页品牌管理 Service
 * @Author AutMaple
 * @Date 2022/7/19 20:28
 * @Version 1.0
 **/
public interface SmsHomeBrandService {
    /**
     * @Author AutMaple
     * @Description 添加首页品牌推荐
     * @Date 2022/7/19 20:29
     **/
    int create(List<SmsHomeBrand> homeBrandList);

    /**
     * @Author AutMaple
     * @Description 修改品牌推荐排序
     * @Date 2022/7/19 20:29
     **/
    int updateSort(Long id, Integer sort);

    /**
     * @Author AutMaple
     * @Description 批量删除品牌推荐
     * @Date 2022/7/19 20:29
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 批量修改推荐状态
     * @Date 2022/7/19 20:30
     **/
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * @Author AutMaple
     * @Description 分页查询品牌推荐
     * @Date 2022/7/19 20:31
     **/
    List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize,Integer pageNum);
}

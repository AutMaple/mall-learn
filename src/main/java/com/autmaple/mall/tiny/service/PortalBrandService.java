package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.mbg.model.PmsBrand;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;

import java.util.List;

/**
 * @ClassName PortalBrandService
 * @Description 首页品牌推荐管理 Service
 * @Author AutMaple
 * @Date 2022/7/24 11:06
 * @Version 1.0
 **/
public interface PortalBrandService {
    /**
     * @Author AutMaple
     * @Description 分页获取推荐品牌
     * @Date 2022/7/24 11:07
     **/
    List<PmsBrand> recommendList(Integer pageNum, Integer pageSize);

    /**
     * @Author AutMaple
     * @Description 获取品牌详情
     * @Date 2022/7/24 11:07
     **/
    PmsBrand detail(Long brandId);

    /**
     * @Author AutMaple
     * @Description 分页获取品牌关联商品
     * @Date 2022/7/24 11:07
     **/
    CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize);
}

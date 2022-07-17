package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.PmsSkuStock;

import java.util.List;

/**
 * @ClassName PmsSkuStockService
 * @Description 商品 Sku 库存管理 Service
 * @Author AutMaple
 * @Date 2022/7/17 09:44
 * @Version 1.0
 **/
public interface PmsSkuStockService {
    /**
     * @Author AutMaple
     * @Description 根据产品 ID 和 skuCode 关键字模糊搜索
     * @Date 2022/7/17 09:45
     **/
    List<PmsSkuStock> getList(Long pid, String keyword);

    /**
     * @Author AutMaple
     * @Description 批量更新商品库存信息
     * @Date 2022/7/17 09:46
     **/
    int update(Long id, List<PmsSkuStock> skuStockList);
}

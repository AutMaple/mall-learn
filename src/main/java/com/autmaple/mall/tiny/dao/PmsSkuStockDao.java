package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.PmsSkuStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PmsSkuStockDao
 * @Description 商品 SKU 商品自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/16 20:07
 * @Version 1.0
 **/
@Mapper
public interface PmsSkuStockDao {

    /**
     * @Author AutMaple
     * @Description 批量插入操作
     * @Date 2022/7/16 20:08
     **/
    int insertList(@Param("list") List<PmsSkuStock> skuStockList);

    /**
     * @Author AutMaple
     * @Description 批量插入或替换操作
     * @Date 2022/7/16 20:09
     **/
    int replaceList(@Param("list") List<PmsSkuStock> skuStockList);

}

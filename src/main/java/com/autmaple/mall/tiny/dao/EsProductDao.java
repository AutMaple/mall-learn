package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName EsProductDao
 * @Description 搜索系统中的商品管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/6/19 20:39
 * @Version 1.0
 **/
@Mapper
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}

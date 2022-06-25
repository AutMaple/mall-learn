package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.elasticsearch.rest.action.RestActions;
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
      * @Description 从数据中导入所有商品到 ES 中
      * @Date 2022/6/19 20:24
      **/
    int importAll();

    /**
      * @Author AutMaple
      * @Description 根据 id 删除商品
      * @Date 2022/6/19 20:25
      * @param id 商品的唯一标识
      **/
    void delete(Long id);

    /**
      * @Author AutMaple
      * @Description 根据 id 创建商品
      * @Date 2022/6/19 20:26
      * @param id 商品的唯一标识
      **/
    EsProduct create(Long id);

    /**
      * @Author AutMaple
      * @Description 批量删除商品
      * @Date 2022/6/19 20:28
      * @param ids 要删除的 id 列表
      **/
    void delete(List<Long> ids);

    /**
      * @Author AutMaple
      * @Description 根据关键字搜索名称或者副标题
      * @Date 2022/6/19 20:29
      * @param keyword 关键字
      * @param  pageNum 页码
      * @param  pageSize 页面大小
      **/
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}

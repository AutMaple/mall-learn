package com.autmaple.mall.tiny.nosql.elasticsearch.repository;

import com.autmaple.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName EsProductRepository
 * @Description 商品 ES 操作类
 * @Author AutMaple
 * @Date 2022/6/19 20:16
 * @Version 1.0
 **/
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {

    /**
     * @Author AutMaple
     * @Description 搜索查询
     * @Date 2022/6/19 20:21
     * @Param name 商品名称
     * @Param subTitle 商品标题
     * @Param keywords 商品关键字
     * @Param page 分页信息
     **/
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}

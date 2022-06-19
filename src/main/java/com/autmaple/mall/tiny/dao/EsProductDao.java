package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;

/**
 * @ClassName EsProductDao
 * @Description TODO 搜索系统中的商品管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/6/19 20:39
 * @Version 1.0
 **/
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}

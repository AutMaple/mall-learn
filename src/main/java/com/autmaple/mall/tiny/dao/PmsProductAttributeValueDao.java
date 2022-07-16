package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.PmsProductAttributeValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PmsProductAttributeValueDao
 * @Description 商品参数/规格属性自定义 DAO
 * @Author AutMaple
 * @Date 2022/7/16 20:18
 * @Version 1.0
 **/
@Mapper
public interface PmsProductAttributeValueDao {
    /**
     * @Author AutMaple
     * @Description 批量创建
     * @Date 2022/7/16 20:19
     **/
    int insertList(@Param("list") List<PmsProductAttributeValue> productAttributeValueList);
}

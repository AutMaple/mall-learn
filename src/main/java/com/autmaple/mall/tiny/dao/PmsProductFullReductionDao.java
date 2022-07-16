package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.PmsProductFullReduction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PmsProductFullReductionDao
 * @Description 商品満减自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/16 19:56
 * @Version 1.0
 **/
@Mapper
public interface PmsProductFullReductionDao {

    /**
     * @Author AutMaple
     * @Description 批量创建
     * @Date 2022/7/16 20:02
     **/
    int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}

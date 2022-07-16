package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.dto.PmsProductResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName PmsProductDao
 * @Description 商品管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/16 20:41
 * @Version 1.0
 **/
@Mapper
public interface PmsProductDao {
    /**
     * @Author AutMaple
     * @Description 获取商品编辑信息
     * @Date 2022/7/16 20:42
     **/
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}

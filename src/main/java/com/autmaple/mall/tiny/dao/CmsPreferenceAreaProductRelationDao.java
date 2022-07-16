package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.CmsPrefrenceAreaProductRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CmsPreferenceAreaProductRelationDao
 * @Description 优选和商品关系自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/16 20:32
 * @Version 1.0
 **/
@Mapper
public interface CmsPreferenceAreaProductRelationDao {
    /**
     * @Author AutMaple
     * @Description 批量创建
     * @Date 2022/7/16 20:34
     **/
    int insertList(@Param("list") List<CmsPrefrenceAreaProductRelation> preferenceAreaProductRelationList);
}

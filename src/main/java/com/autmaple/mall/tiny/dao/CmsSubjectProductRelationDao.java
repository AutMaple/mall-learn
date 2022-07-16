package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.CmsSubjectProductRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CmsSubjectProductRelationDao
 * @Description 商品和主题关系自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/16 20:26
 * @Version 1.0
 **/
@Mapper
public interface CmsSubjectProductRelationDao {

    /**
     * @Author AutMaple
     * @Description 批量创建
     * @Date 2022/7/16 20:27
     **/
    int insertList(@Param("list") List<CmsSubjectProductRelation> subjectProductRelationList);
}

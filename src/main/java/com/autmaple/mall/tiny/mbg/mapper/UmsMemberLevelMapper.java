package com.autmaple.mall.tiny.mbg.mapper;

import com.autmaple.mall.tiny.mbg.model.UmsMemberLevel;
import com.autmaple.mall.tiny.mbg.model.UmsMemberLevelExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UmsMemberLevelMapper {
    long countByExample(UmsMemberLevelExample example);

    int deleteByExample(UmsMemberLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberLevel record);

    int insertSelective(UmsMemberLevel record);

    List<UmsMemberLevel> selectByExample(UmsMemberLevelExample example);

    UmsMemberLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberLevel record, @Param("example") UmsMemberLevelExample example);

    int updateByExample(@Param("record") UmsMemberLevel record, @Param("example") UmsMemberLevelExample example);

    int updateByPrimaryKeySelective(UmsMemberLevel record);

    int updateByPrimaryKey(UmsMemberLevel record);
}
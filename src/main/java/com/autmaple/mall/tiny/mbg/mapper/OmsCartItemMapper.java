package com.autmaple.mall.tiny.mbg.mapper;

import com.autmaple.mall.tiny.mbg.model.OmsCartItem;
import com.autmaple.mall.tiny.mbg.model.OmsCartItemExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OmsCartItemMapper {
    long countByExample(OmsCartItemExample example);

    int deleteByExample(OmsCartItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsCartItem record);

    int insertSelective(OmsCartItem record);

    List<OmsCartItem> selectByExample(OmsCartItemExample example);

    OmsCartItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsCartItem record, @Param("example") OmsCartItemExample example);

    int updateByExample(@Param("record") OmsCartItem record, @Param("example") OmsCartItemExample example);

    int updateByPrimaryKeySelective(OmsCartItem record);

    int updateByPrimaryKey(OmsCartItem record);
}
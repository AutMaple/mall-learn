package com.autmaple.mall.tiny.mbg.mapper;

import com.autmaple.mall.tiny.mbg.model.OmsCompanyAddress;
import com.autmaple.mall.tiny.mbg.model.OmsCompanyAddressExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OmsCompanyAddressMapper {
    long countByExample(OmsCompanyAddressExample example);

    int deleteByExample(OmsCompanyAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsCompanyAddress record);

    int insertSelective(OmsCompanyAddress record);

    List<OmsCompanyAddress> selectByExample(OmsCompanyAddressExample example);

    OmsCompanyAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsCompanyAddress record, @Param("example") OmsCompanyAddressExample example);

    int updateByExample(@Param("record") OmsCompanyAddress record, @Param("example") OmsCompanyAddressExample example);

    int updateByPrimaryKeySelective(OmsCompanyAddress record);

    int updateByPrimaryKey(OmsCompanyAddress record);
}
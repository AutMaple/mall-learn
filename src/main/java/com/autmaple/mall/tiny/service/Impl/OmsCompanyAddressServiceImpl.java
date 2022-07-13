package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.mapper.OmsCompanyAddressMapper;
import com.autmaple.mall.tiny.mbg.model.OmsCompanyAddress;
import com.autmaple.mall.tiny.mbg.model.OmsCompanyAddressExample;
import com.autmaple.mall.tiny.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OmsCompanyAddressServiceImpl
 * @Description 收货地址管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/13 19:52
 * @Version 1.0
 **/
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {

    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;

    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }
}

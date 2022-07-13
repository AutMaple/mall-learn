package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.OmsCompanyAddress;

import java.util.List;

/**
 * @ClassName OmsCompanyAddressService
 * @Description 收货地址管理 Service
 * @Author AutMaple
 * @Date 2022/7/13 19:50
 * @Version 1.0
 **/
public interface OmsCompanyAddressService {

    /**
     * @Author AutMaple
     * @Description 获取全部收货地址
     * @Date 2022/7/13 19:51
     **/
    List<OmsCompanyAddress> list();
}

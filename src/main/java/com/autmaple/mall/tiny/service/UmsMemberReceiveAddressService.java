package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.UmsMemberReceiveAddress;

import java.util.List;

/**
 * @ClassName UmsMemberReceiveAddressService
 * @Description 用户地址管理 Service
 * @Author AutMaple
 * @Date 2022/7/23 21:18
 * @Version 1.0
 **/
public interface UmsMemberReceiveAddressService {
    /**
     * @Author AutMaple
     * @Description 添加收货地址 
     * @Date 2022/7/23 21:19
     **/
    int add(UmsMemberReceiveAddress address);
    
    /**
     * @Author AutMaple
     * @Description 删除收获地址
     * @Date 2022/7/23 21:19
     **/
    int delete(Long id);
    
    /**
     * @Author AutMaple
     * @Description 修改收获地址
     * @Date 2022/7/23 21:20
     * @param id 地址表的 ID
     **/
    int update(Long id, UmsMemberReceiveAddress address);
    
    /**
     * @Author AutMaple
     * @Description 返回当前用户的收获地址
     * @Date 2022/7/23 21:21
     **/
    List<UmsMemberReceiveAddress> list();

    /**
     * @Author AutMaple
     * @Description 获取地址的详细信息
     * @Date 2022/7/23 21:21
     **/
    UmsMemberReceiveAddress getItem(Long id);
}

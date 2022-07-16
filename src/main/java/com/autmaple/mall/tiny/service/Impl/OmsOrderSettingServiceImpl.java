package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.mapper.OmsOrderSettingMapper;
import com.autmaple.mall.tiny.mbg.model.OmsOrderSetting;
import com.autmaple.mall.tiny.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OmsOrderSettingServiceImpl
 * @Description 订单设置管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/16 08:55
 * @Version 1.0
 **/
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {
    @Autowired
    private OmsOrderSettingMapper orderSettingMapper;

    @Override
    public OmsOrderSetting getItem(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        return orderSettingMapper.updateByPrimaryKey(orderSetting);
    }
}

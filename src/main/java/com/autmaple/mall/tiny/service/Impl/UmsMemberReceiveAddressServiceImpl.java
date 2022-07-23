package com.autmaple.mall.tiny.service.Impl;


import cn.hutool.core.collection.CollUtil;
import com.autmaple.mall.tiny.mbg.mapper.UmsMemberReceiveAddressMapper;
import com.autmaple.mall.tiny.mbg.model.UmsMember;
import com.autmaple.mall.tiny.mbg.model.UmsMemberReceiveAddress;
import com.autmaple.mall.tiny.mbg.model.UmsMemberReceiveAddressExample;
import com.autmaple.mall.tiny.service.UmsMemberReceiveAddressService;
import com.autmaple.mall.tiny.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UmsMemberReceiveAddressService
 * @Description 用户地址管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/23 21:22
 * @Version 1.0
 **/
@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberReceiveAddressMapper addressMapper;

    @Override
    public int add(UmsMemberReceiveAddress address) {
        UmsMember currentMember = memberService.getCurrentMember();
        address.setMemberId(currentMember.getId());
        return addressMapper.insert(address);
    }

    @Override
    public int delete(Long id) {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.or().andMemberIdEqualTo(currentMember.getId())
                .andIdEqualTo(id);
        return addressMapper.deleteByExample(example);
    }

    @Override
    public int update(Long id, UmsMemberReceiveAddress address) {
        address.setId(id);
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.or().andMemberIdEqualTo(currentMember.getId())
                .andDefaultStatusEqualTo(1);
        if(address.getDefaultStatus() == 1){
            UmsMemberReceiveAddress record = new UmsMemberReceiveAddress();
            record.setDefaultStatus(0);
            UmsMemberReceiveAddressExample updateExample = new UmsMemberReceiveAddressExample();
            updateExample.or().andMemberIdEqualTo(currentMember.getId())
                    .andDefaultStatusEqualTo(1);
            addressMapper.updateByExampleSelective(record, updateExample);
        }
        return addressMapper.updateByExampleSelective(address, example);
    }

    @Override
    public List<UmsMemberReceiveAddress> list() {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.or().andMemberIdEqualTo(currentMember.getId());
        return addressMapper.selectByExample(example);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long id) {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.or().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
        List<UmsMemberReceiveAddress> addressList = addressMapper.selectByExample(example);
        return CollUtil.isNotEmpty(addressList) ? addressList.get(0) : null;
    }
}

package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.mapper.UmsMemberLevelMapper;
import com.autmaple.mall.tiny.mbg.model.UmsMemberLevel;
import com.autmaple.mall.tiny.mbg.model.UmsMemberLevelExample;
import com.autmaple.mall.tiny.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UmsMemberLevelServiceImpl
 * @Description 会员等级管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/20 20:31
 * @Version 1.0
 **/
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {

    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.or().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}

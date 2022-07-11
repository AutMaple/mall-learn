package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.mapper.CmsPrefrenceAreaMapper;
import com.autmaple.mall.tiny.mbg.model.CmsPrefrenceArea;
import com.autmaple.mall.tiny.mbg.model.CmsPrefrenceAreaExample;
import com.autmaple.mall.tiny.service.CmsPreferenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CmsPreferenceAreaServiceImpl
 * @Description 优选专区管理 Service 的实现类
 * @Author AutMaple
 * @Date 2022/7/11 21:08
 * @Version 1.0
 **/
@Service
public class CmsPreferenceAreaServiceImpl implements CmsPreferenceAreaService {

    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}

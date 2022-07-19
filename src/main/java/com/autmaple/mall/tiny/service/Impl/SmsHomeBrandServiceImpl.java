package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.mbg.mapper.SmsHomeBrandMapper;
import com.autmaple.mall.tiny.mbg.model.SmsHomeBrand;
import com.autmaple.mall.tiny.mbg.model.SmsHomeBrandExample;
import com.autmaple.mall.tiny.service.SmsHomeBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SmsHomeBrandServiceImpl
 * @Description 首页品牌管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/19 20:31
 * @Version 1.0
 **/
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
    @Autowired
    private SmsHomeBrandMapper brandMapper;

    @Override
    public int create(List<SmsHomeBrand> homeBrandList) {
        for (SmsHomeBrand brand : homeBrandList) {
            brand.setRecommendStatus(1);
            brand.setSort(1);
            brandMapper.insert(brand);
        }
        return homeBrandList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand brand = new SmsHomeBrand();
        brand.setId(id);
        brand.setSort(sort);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.or().andIdIn(ids);
        return brandMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.or().andIdIn(ids);
        SmsHomeBrand brand = new SmsHomeBrand();
        brand.setRecommendStatus(recommendStatus);
        return brandMapper.updateByExample(brand, example);
    }

    @Override
    public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        SmsHomeBrandExample.Criteria criteria = example.or();
        if (StrUtil.isNotEmpty(brandName))
            criteria.andBrandNameLike("%" + brandName + "%");
        if (recommendStatus != null)
            criteria.andRecommendStatusEqualTo(recommendStatus);
        example.setOrderByClause("sort desc");
        return brandMapper.selectByExample(example);
    }
}

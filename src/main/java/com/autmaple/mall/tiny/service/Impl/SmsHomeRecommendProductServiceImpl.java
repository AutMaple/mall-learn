package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.mbg.mapper.SmsHomeRecommendProductMapper;
import com.autmaple.mall.tiny.mbg.model.SmsHomeRecommendProduct;
import com.autmaple.mall.tiny.mbg.model.SmsHomeRecommendProductExample;
import com.autmaple.mall.tiny.service.SmsHomeRecommendProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SmsHomeRecommendProductServiceImpl
 * @Description
 * @Author AutMaple
 * @Date 2022/7/19 21:29
 * @Version 1.0
 **/
@Service
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {
    @Autowired
    private SmsHomeRecommendProductMapper productMapper;


    @Override
    public int create(List<SmsHomeRecommendProduct> productList) {
        for (SmsHomeRecommendProduct product : productList) {
            product.setRecommendStatus(1);
            product.setSort(0);
            productMapper.insert(product);
        }
        return productList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct product = new SmsHomeRecommendProduct();
        product.setId(id);
        product.setSort(sort);
        return productMapper.updateByPrimaryKey(product);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.or().andIdIn(ids);
        return productMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendProduct product = new SmsHomeRecommendProduct();
        product.setRecommendStatus(recommendStatus);
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.or().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        SmsHomeRecommendProductExample.Criteria criteria = example.or();
        if (StrUtil.isNotEmpty(productName))
            criteria.andProductNameLike("%" + productName + "%");
        if (recommendStatus != null)
            criteria.andRecommendStatusEqualTo(recommendStatus);
        example.setOrderByClause("sort desc");
        return productMapper.selectByExample(example);
    }
}

package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.mbg.mapper.SmsHomeNewProductMapper;
import com.autmaple.mall.tiny.mbg.model.SmsHomeNewProduct;
import com.autmaple.mall.tiny.mbg.model.SmsHomeNewProductExample;
import com.autmaple.mall.tiny.service.SmsHomeNewProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.support.ExampleMatcherAccessor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SmsHomeNewProductServiceImpl
 * @Description 首页新品推荐 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/19 21:01
 * @Version 1.0
 **/
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {
    @Autowired
    private SmsHomeNewProductMapper productMapper;

    @Override
    public int create(List<SmsHomeNewProduct> homeNewProductList) {
        for (SmsHomeNewProduct product : homeNewProductList) {
            product.setRecommendStatus(1);
            product.setSort(0);
            productMapper.insert(product);
        }
        return homeNewProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct product = new SmsHomeNewProduct();
        product.setId(id);
        product.setSort(sort);
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.or().andIdIn(ids);
        return productMapper.deleteByExample(example);

    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeNewProduct product = new SmsHomeNewProduct();
        product.setRecommendStatus(recommendStatus);
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.or().andIdIn(ids);
        return productMapper.updateByExampleSelective(product,example);
    }

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        SmsHomeNewProductExample.Criteria criteria = example.or();
        if(StrUtil.isNotEmpty(productName))
            criteria.andProductNameLike("%" + productName + "%");
        if(recommendStatus != null)
            criteria.andRecommendStatusEqualTo(recommendStatus);
        example.setOrderByClause("sort desc");
        return productMapper.selectByExample(example);
    }
}

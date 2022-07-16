package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.dto.PmsBrandParam;
import com.autmaple.mall.tiny.mbg.mapper.PmsBrandMapper;
import com.autmaple.mall.tiny.mbg.mapper.PmsProductMapper;
import com.autmaple.mall.tiny.mbg.model.PmsBrand;
import com.autmaple.mall.tiny.mbg.model.PmsBrandExample;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.mbg.model.PmsProductExample;
import com.autmaple.mall.tiny.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PmsBrandServiceImpl
 * @Description 品牌管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/16 10:47
 * @Version 1.0
 **/
@Service
public class PmsBrandServiceImpl implements PmsBrandService {


    @Autowired
    private PmsBrandMapper brandMapper;

    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrandParam brandParam) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(brandParam, brand);
        if (StrUtil.isEmpty(brandParam.getFirstLetter())) {
            brand.setFirstLetter(brandParam.getName().substring(0, 1));
        }
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrandParam brandParam) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(brandParam, brand);
        brand.setId(id);
        if (StrUtil.isEmpty(brandParam.getFirstLetter())) {
            brand.setFirstLetter(brandParam.getName().substring(0, 1));
        }
        // 更新品牌时要更新商品中的品牌名称
        PmsProduct product = new PmsProduct();
        product.setBrandName(brand.getName());
        PmsProductExample example = new PmsProductExample();
        example.or().andBrandIdEqualTo(id);
        productMapper.updateByExampleSelective(product, example);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        PmsBrandExample example = new PmsBrandExample();
        example.or().andIdIn(ids);
        return brandMapper.deleteByExample(example);
    }

    @Override
    public List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample example = new PmsBrandExample();
        example.setOrderByClause("sort desc");
        if (StrUtil.isNotEmpty(keyword)) {
            example.or().andNameLike("%" + keyword + "%");
        }
        return brandMapper.selectByExample(example);
    }


    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand brand = new PmsBrand();
        brand.setShowStatus(showStatus);
        PmsBrandExample example = new PmsBrandExample();
        example.or().andIdIn(ids);
        return brandMapper.updateByExampleSelective(brand, example);
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand brand = new PmsBrand();
        brand.setFactoryStatus(factoryStatus);
        PmsBrandExample example = new PmsBrandExample();
        example.or().andIdIn(ids);
        return brandMapper.updateByExampleSelective(brand, example);
    }
}

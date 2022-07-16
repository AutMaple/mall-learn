package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dao.PmsProductAttributeDao;
import com.autmaple.mall.tiny.dto.PmsProductAttributeParam;
import com.autmaple.mall.tiny.dto.ProductAttrInfo;
import com.autmaple.mall.tiny.mbg.mapper.PmsProductAttributeCategoryMapper;
import com.autmaple.mall.tiny.mbg.mapper.PmsProductAttributeMapper;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttribute;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttributeCategory;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttributeExample;
import com.autmaple.mall.tiny.service.PmsProductAttributeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PmsProductAttributeServiceImpl
 * @Description 商品属性管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/16 12:59
 * @Version 1.0
 **/
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {

    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;

    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Autowired
    private PmsProductAttributeDao productAttributeDao;

    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer PageNum) {
        PageHelper.startPage(PageNum, pageSize);
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.setOrderByClause("sort desc");
        example.or().andProductAttributeCategoryIdEqualTo(cid).andTypeEqualTo(type);
        return productAttributeMapper.selectByExample(example);
    }

    @Override
    public int create(PmsProductAttributeParam productAttribute) {
        PmsProductAttribute attribute = new PmsProductAttribute();
        BeanUtils.copyProperties(productAttribute, attribute);
        int count = productAttributeMapper.insertSelective(attribute);
        PmsProductAttributeCategory category = productAttributeCategoryMapper.selectByPrimaryKey(attribute.getProductAttributeCategoryId());
        if (attribute.getType() == 0) {
            category.setParamCount(category.getAttributeCount() + 1);
        } else if (attribute.getType() == 1) {
            category.setParamCount(category.getParamCount() + 1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(category);
        return count;
    }

    @Override
    public int update(Long id, PmsProductAttributeParam productAttribute) {
        PmsProductAttribute attribute = new PmsProductAttribute();
        attribute.setId(id);
        BeanUtils.copyProperties(productAttribute, attribute);
        return productAttributeMapper.updateByPrimaryKeySelective(attribute);
    }

    @Override
    public PmsProductAttribute getItem(Long id) {
        return productAttributeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        PmsProductAttribute attribute = productAttributeMapper.selectByPrimaryKey(ids.get(0));
        Integer type = attribute.getType();
        PmsProductAttributeCategory category = productAttributeCategoryMapper.selectByPrimaryKey(attribute.getProductAttributeCategoryId());

        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.or().andIdIn(ids);
        int count = productAttributeMapper.deleteByExample(example);
        if (type == 0) {
            if (category.getAttributeCount() >= count)
                category.setAttributeCount(category.getAttributeCount() - count);
            else
                category.setAttributeCount(0);
        } else if (type == 1) {
            if (category.getParamCount() >= count)
                category.setParamCount(category.getParamCount() - count);
            else
                category.setParamCount(0);
        }
        productAttributeCategoryMapper.updateByPrimaryKeySelective(category);
        return count;
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeDao.getProductAttrInfo(productCategoryId);
    }
}

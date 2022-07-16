package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dao.PmsProductAttributeCategoryDao;
import com.autmaple.mall.tiny.dto.PmsProductAttributeCategoryItem;
import com.autmaple.mall.tiny.mbg.mapper.PmsProductAttributeCategoryMapper;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttributeCategory;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttributeCategoryExample;
import com.autmaple.mall.tiny.service.PmsProductAttributeCategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PmsProductAttributeCategoryServiceImpl
 * @Description 商品属性分类管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/16 12:20
 * @Version 1.0
 **/
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Autowired
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insertSelective(productAttributeCategory);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory category = new PmsProductAttributeCategory();
        category.setId(id);
        category.setName(name);
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return productAttributeCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return productAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample());
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryDao.getListWithAttr();
    }
}

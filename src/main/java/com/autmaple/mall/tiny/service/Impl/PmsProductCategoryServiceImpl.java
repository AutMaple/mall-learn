package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dao.PmsProductCategoryAttributeRelationDao;
import com.autmaple.mall.tiny.dao.PmsProductCategoryDao;
import com.autmaple.mall.tiny.dto.PmsProductCategoryParam;
import com.autmaple.mall.tiny.dto.PmsProductCategoryWithChildrenItem;
import com.autmaple.mall.tiny.mbg.mapper.PmsProductCategoryAttributeRelationMapper;
import com.autmaple.mall.tiny.mbg.mapper.PmsProductCategoryMapper;
import com.autmaple.mall.tiny.mbg.mapper.PmsProductMapper;
import com.autmaple.mall.tiny.mbg.model.*;
import com.autmaple.mall.tiny.service.PmsProductCategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PmsProductCategoryServiceImpl
 * @Description 商品分类管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/16 17:11
 * @Version 1.0
 **/
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {

    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    @Autowired
    private PmsProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Autowired
    private PmsProductCategoryDao productCategoryDao;

    @Override
    public int create(PmsProductCategoryParam productCategoryParam) {
        PmsProductCategory category = new PmsProductCategory();
        category.setProductCount(0);
        BeanUtils.copyProperties(productCategoryParam, category);
        // 没有父类时为一级分类
        setCategoryLevel(category);
        int count = productCategoryMapper.insertSelective(category);
        List<Long> productAttributeIdList = productCategoryParam.getProductAttributeIdList();
        if (!CollectionUtils.isEmpty(productAttributeIdList)) {
            insertRelationList(category.getId(), productAttributeIdList);
        }
        return count;
    }

    /**
     * @param productAttributeIdList 相关商品筛选属性 id 集合
     * @param productCategoryId      属性分类 id
     * @Author AutMaple
     * @Description 批量插入商品分类于筛选属性关系表
     * @Date 2022/7/16 17:29
     **/
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttributeId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductCategoryId(productCategoryId);
            relation.setProductAttributeId(productAttributeId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationDao.insertList(relationList);
    }

    @Override
    public int update(Long id, PmsProductCategoryParam productCategoryParam) {
        PmsProductCategory category = new PmsProductCategory();
        category.setId(id);
        BeanUtils.copyProperties(productCategoryParam, category);
        setCategoryLevel(category);
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(category.getName());
        PmsProductExample example = new PmsProductExample();
        example.or().andProductCategoryIdEqualTo(id);
        productMapper.updateByExampleSelective(product, example);
        //同时更新筛选属性的信息
        PmsProductCategoryAttributeRelationExample relationExample = new PmsProductCategoryAttributeRelationExample();
        relationExample.or().andProductCategoryIdEqualTo(id);
        productCategoryAttributeRelationMapper.deleteByExample(relationExample);
        if (!CollectionUtils.isEmpty(productCategoryParam.getProductAttributeIdList()))
            insertRelationList(id, productCategoryParam.getProductAttributeIdList());

        return productCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.setOrderByClause("sort desc");
        example.or().andParentIdEqualTo(parentId);
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public int delete(Long id) {
        return productCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProductCategory getItem(Long id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsProductCategory category = new PmsProductCategory();
        category.setNavStatus(navStatus);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.or().andIdIn(ids);
        return productCategoryMapper.updateByExampleSelective(category, example);

    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsProductCategory category = new PmsProductCategory();
        category.setShowStatus(showStatus);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.or().andIdIn(ids);
        return productCategoryMapper.updateByExampleSelective(category, example);
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }

    private void setCategoryLevel(PmsProductCategory category) {
        if (category.getParentId() == 0) {
            category.setLevel(0);
        } else {
            PmsProductCategory parentCategory = productCategoryMapper.selectByPrimaryKey(category.getId());
            if (parentCategory != null)
                category.setLevel(parentCategory.getLevel() + 1);
            else
                category.setLevel(0);
        }
    }
}

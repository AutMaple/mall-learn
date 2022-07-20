package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.mapper.UmsResourceCategoryMapper;
import com.autmaple.mall.tiny.mbg.model.UmsResourceCategory;
import com.autmaple.mall.tiny.mbg.model.UmsResourceCategoryExample;
import com.autmaple.mall.tiny.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UmsResourceCategoryServiceImpl
 * @Description 后台资源分类管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/20 21:22
 * @Version 1.0
 **/
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Autowired
    private UmsResourceCategoryMapper categoryMapper;

    @Override
    public List<UmsResourceCategory> listAll() {
        UmsResourceCategoryExample example = new UmsResourceCategoryExample();
        example.setOrderByClause("sort desc");
        return categoryMapper.selectByExample(example);

    }

    @Override
    public int create(UmsResourceCategory resourceCategory) {
        resourceCategory.setCreateTime(new Date());
        return categoryMapper.insert(resourceCategory);
    }

    @Override
    public int update(Long id, UmsResourceCategory resourceCategory) {
        resourceCategory.setId(id);
        return categoryMapper.updateByPrimaryKeySelective(resourceCategory);
    }

    @Override
    public int delete(Long id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }
}

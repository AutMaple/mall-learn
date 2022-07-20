package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.mbg.mapper.UmsResourceMapper;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import com.autmaple.mall.tiny.mbg.model.UmsResourceExample;
import com.autmaple.mall.tiny.service.UmsAdminCacheService;
import com.autmaple.mall.tiny.service.UmsResourceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UmsResourceServiceImpl
 * @Description 后台资源管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/20 21:38
 * @Version 1.0
 **/
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public int create(UmsResource resource) {
        resource.setCreateTime(new Date());
        return resourceMapper.insert(resource);
    }

    @Override
    public int update(Long id, UmsResource resource) {
        resource.setId(id);
        return resourceMapper.updateByPrimaryKey(resource);
    }

    @Override
    public UmsResource getItem(Long id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.or();
        if (categoryId != null)
            criteria.andCategoryIdEqualTo(categoryId);
        if (StrUtil.isNotEmpty(nameKeyword))
            criteria.andNameLike("%" + nameKeyword + "%");
        if (StrUtil.isNotEmpty(urlKeyword))
            criteria.andUrlLike("%" + urlKeyword + "%");
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }
}

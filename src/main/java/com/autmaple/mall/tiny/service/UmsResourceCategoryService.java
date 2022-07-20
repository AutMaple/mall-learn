package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.UmsResourceCategory;

import java.util.List;

/**
 * @ClassName UmsResourceCategoryService
 * @Description 后台资源分类管理 Service
 * @Author AutMaple
 * @Date 2022/7/20 21:19
 * @Version 1.0
 **/
public interface UmsResourceCategoryService {
    /**
     * @Author AutMaple
     * @Description 获取所有的资源分类
     * @Date 2022/7/20 21:20
     **/
    List<UmsResourceCategory> listAll();

    /**
     * @Author AutMaple
     * @Description 创建资源分类
     * @Date 2022/7/20 21:20
     **/
    int create(UmsResourceCategory resourceCategory);

    /**
     * @Author AutMaple
     * @Description 修改后台资源分类
     * @Date 2022/7/20 21:21
     **/
    int update(Long id, UmsResourceCategory resourceCategory);

    /**
     * @Author AutMaple
     * @Description 删除资源分类
     * @Date 2022/7/20 21:21
     **/
    int delete(Long id);
}

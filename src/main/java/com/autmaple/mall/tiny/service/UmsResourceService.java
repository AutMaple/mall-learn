package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.UmsResource;

import java.util.List;

/**
 * @ClassName UmsResourceService
 * @Description 后台资源管理 Service
 * @Author AutMaple
 * @Date 2022/7/20 21:33
 * @Version 1.0
 **/
public interface UmsResourceService {
    /**
     * @Author AutMaple
     * @Description 创建资源
     * @Date 2022/7/20 21:34
     **/
    int create(UmsResource resource);

    /**
     * @Author AutMaple
     * @Description 更新后台资源
     * @Date 2022/7/20 21:34
     **/
    int update(Long id, UmsResource resource);

    /**
     * @Author AutMaple
     * @Description 获取资源的详细信息
     * @Date 2022/7/20 21:35
     **/
    UmsResource getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 删除资源
     * @Date 2022/7/20 21:35
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 分页查询资源
     * @Date 2022/7/20 21:36
     **/
    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 查询所有的后台资源
     * @Date 2022/7/20 21:37
     **/
    List<UmsResource> listAll();
}

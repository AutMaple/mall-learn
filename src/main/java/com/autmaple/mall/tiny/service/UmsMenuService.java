package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.UmsMenuNode;
import com.autmaple.mall.tiny.mbg.model.UmsMenu;

import java.util.List;

/**
 * @ClassName UmsMenuService
 * @Description 后台菜单管理 Service
 * @Author AutMaple
 * @Date 2022/7/20 20:40
 * @Version 1.0
 **/
public interface UmsMenuService {
    /**
     * @Author AutMaple
     * @Description 创建后台菜单
     * @Date 2022/7/20 20:42
     **/
    int create(UmsMenu menu);

    /**
     * @Author AutMaple
     * @Description 更新后台菜单
     * @Date 2022/7/20 20:42
     **/
    int update(Long id, UmsMenu menu);

    /**
     * @Author AutMaple
     * @Description 根据 ID 获取菜单详情
     * @Date 2022/7/20 20:43
     **/
    UmsMenu getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 根据 ID 删除菜单
     * @Date 2022/7/20 20:44
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 分页查询后台菜单
     * @Date 2022/7/20 20:44
     **/
    List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 树形结构返回所有后台菜单
     * @Date 2022/7/20 20:45
     **/
    List<UmsMenuNode> treeList();

    /**
     * @Author AutMaple
     * @Description 修改菜单的显示状态
     * @Date 2022/7/20 20:47
     **/
    int updateHidden(Long id, Integer hidden);
}

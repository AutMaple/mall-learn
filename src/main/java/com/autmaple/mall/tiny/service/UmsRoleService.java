package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.UmsMenu;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import com.autmaple.mall.tiny.mbg.model.UmsRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UmsRoleService
 * @Description 后台角色管理 Service
 * @Author AutMaple
 * @Date 2022/7/10 20:32
 * @Version 1.0
 **/
public interface UmsRoleService {
    /**
     * @Author AutMaple
     * @Description 添加角色
     * @Date 2022/7/10 20:33
     **/
    int create(UmsRole role);

    /**
     * @Author AutMaple
     * @Description 修改角色信息
     * @Date 2022/7/10 20:35
     **/
    int update(Long id, UmsRole role);

    /**
     * @Author AutMaple
     * @Description 批量删除角色
     * @Date 2022/7/10 20:36
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 获取角色列表
     * @Date 2022/7/10 20:36
     **/
    List<UmsRole> list();

    /**
     * @Author AutMaple
     * @Description 分页获取角色列表
     * @Date 2022/7/10 20:37
     **/
    List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 根据管理员 ID 获取对应的菜单
     * @Date 2022/7/10 20:38
     **/
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * @Author AutMaple
     * @Description 获取角色相关的菜单
     * @Date 2022/7/10 20:38
     **/
    List<UmsMenu> listMenu(Long roleId);

    /**
     * @Author AutMaple
     * @Description 获取角色相关的资源
     * @Date 2022/7/10 20:39
     **/
    List<UmsResource> listResource(Long roleId);

    /**
     * @Author AutMaple
     * @Description 给角色分配菜单
     * @Date 2022/7/10 20:40
     **/
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * @Author AutMaple
     * @Description 给角色分配菜单
     * @Date 2022/7/10 20:40
     **/
    int allocResource(Long roleId, List<Long> resourceIds);

}

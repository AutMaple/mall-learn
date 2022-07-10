package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.UmsAdmin;
import com.autmaple.mall.tiny.mbg.model.UmsResource;

import java.util.List;

/**
 * @ClassName UmsAdminCacheService
 * @Description 后台用户缓存操作 Service
 * @Author AutMaple
 * @Date 2022/7/10 11:28
 * @Version 1.0
 **/
public interface UmsAdminCacheService {

    /**
     * @param adminId 后台用户 ID
     * @Author AutMaple
     * @Description 删除后台用户缓存
     * @Date 2022/7/10 11:29
     **/
    void deleteAdmin(Long adminId);

    /**
     * @param adminId 后台用户 ID
     * @Author AutMaple
     * @Description 删除用户资源列表缓存
     * @Date 2022/7/10 11:31
     **/
    void deleteResourceList(Long adminId);

    /**
     * @param roleId 角色 ID
     * @Author AutMaple
     * @Description 当角色相关资源信息改变时，删除相关后台用户缓存
     * @Date 2022/7/10 11:34
     **/
    void deleteResourceListByRole(Long roleId);

    /**
     * @param roleIds 角色ID列表
     * @Author AutMaple
     * @Description 当角色相关资源信息改变时，删除相关后台用户缓存
     * @Date 2022/7/10 11:35
     **/
    void deleteResourceListByRoleIds(List<Long> roleIds);

    /**
     * @param resourceId 资源 ID
     * @Author AutMaple
     * @Description 当资源信息改变时，删除该资源相关的缓存
     * @Date 2022/7/10 11:37
     **/
    void deleteResourceListByResource(Long resourceId);

    /**
     * @param username 后台用户的用户名
     * @return 用户名所对应用户的全部信息
     * @Author AutMaple
     * @Description 获取缓存中的用户信息
     * @Date 2022/7/10 11:38
     **/
    UmsAdmin getAdmin(String username);


    void setAdmin(UmsAdmin admin);

    /**
     * @param adminId 后台用户 ID
     * @return 用户对应的资源集合
     * @Author AutMaple
     * @Description 获取用户在缓存中的资源
     * @Date 2022/7/10 11:40
     **/
    List<UmsResource> getResourceList(Long adminId);

    /**
     * @param adminId      后台用户 ID
     * @param resourceList 后台用户拥有的资源
     * @Author AutMaple
     * @Description 将指定用户拥有的资源存入缓存中
     * @Date 2022/7/10 11:41
     **/
    void setResourceList(Long adminId, List<UmsResource> resourceList);
}

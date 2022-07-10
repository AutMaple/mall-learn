package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.UmsMenu;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UmsRoleDao
 * @Description 后台角色管理自定义 Dao
 * @Author AutMaple
 * @Date 2022/7/10 20:46
 * @Version 1.0
 **/
@Mapper
public interface UmsRoleDao {
    /**
     * @Author AutMaple
     * @Description 根据后台用户ID获取菜单
     * @Date 2022/7/10 20:48
     **/
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    /**
     * @Author AutMaple
     * @Description 根据角色 ID 获取菜单
     * @Date 2022/7/10 20:48
     **/
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * @Author AutMaple
     * @Description 根据角色 ID 获取资源
     * @Date 2022/7/10 20:49
     **/
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}

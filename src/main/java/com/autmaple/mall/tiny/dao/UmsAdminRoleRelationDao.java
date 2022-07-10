package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.UmsAdminRoleRelation;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import com.autmaple.mall.tiny.mbg.model.UmsRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义 Dao
 */
@Mapper
public interface UmsAdminRoleRelationDao {
    /**
     * @Author AutMaple
     * @Description 批量插入用户角色关系
     * @Date 2022/7/10 15:29
     **/
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * @Author AutMaple
     * @Description 获取用户角色列表
     * @Date 2022/7/10 15:30
     **/
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * @Author AutMaple
     * @Description 获取用户资源列表
     * @Date 2022/7/10 15:31
     **/
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * @Author AutMaple
     * @Description 获取与指定资源相关的用户 ID
     * @Date 2022/7/10 15:32
     **/
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}

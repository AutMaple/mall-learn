package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.UpdateAdminPasswordParam;
import com.autmaple.mall.tiny.mbg.model.UmsAdmin;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import com.autmaple.mall.tiny.mbg.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 后台管理员 Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回 JWT 格式的 Token
     */
    String login(String username, String password);

    /**
     * @param oldToken 旧的 Token
     * @return 新的 Token
     * @Author AutMaple
     * @Description 刷新 Token
     * @Date 2022/7/10 11:55
     **/
    String refreshToken(String oldToken);

    /**
     * @Author AutMaple
     * @Description 根据用户 Id 获取用户
     * @Date 2022/7/10 11:56
     **/
    UmsAdmin getItem(Long id);

    /**
     * @param keyword 用户名或者昵称
     * @return 查询到的用户列表
     * @Author AutMaple
     * @Description 根据用户名或者昵称查询用户
     * @Date 2022/7/10 11:57
     **/
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * @param id    用户唯一标识
     * @param admin 用户最新信息实体
     * @return 返回 0 -> 修改失败，返回 1-> 修改成功
     * @Author AutMaple
     * @Description 修改指定用户的用户信息
     * @Date 2022/7/10 11:58
     **/
    int update(Long id, UmsAdmin admin);

    /**
     * @Author AutMaple
     * @Description 删除指定的用户
     * @Date 2022/7/10 15:18
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 更新用户角色关系
     * @Date 2022/7/10 15:23
     **/
    int updateRole(Long adminId, List<Long> roleIds);


    /**
     * @param adminId 用户唯一标识
     * @return 返回对应的角色列表
     * @Author AutMaple
     * @Description 获取用户对应的角色
     * @Date 2022/7/10 12:01
     **/
    List<UmsRole> getRoleList(Long adminId);

    /**
     * @param adminId 用户唯一标识
     * @return 返回用户所拥有的资源列表
     * @Author AutMaple
     * @Description 获取用户拥有的资源列表
     * @Date 2022/7/10 12:02
     **/
    List<UmsResource> getResourceList(Long adminId);


    /**
     * @param updateAdminPasswordParam 请求参数对应的实体类
     * @return 0 -> 修改失败 1-> 修改成功
     * @Author AutMaple
     * @Description 修改密码
     * @Date 2022/7/10 12:07
     **/
    int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam);

    /**
     * @param username 用户名
     * @return SpringSecurity 提供的 UserDetails 类
     * @Author AutMaple
     * @Description 获取用户信息
     * @Date 2022/7/10 12:09
     **/
    UserDetails loadUserByUsername(String username);

    /**
     * @Author AutMaple
     * @Description 获取缓存服务
     * @Date 2022/7/10 12:10
     **/
    UmsAdminCacheService getCacheService();
}

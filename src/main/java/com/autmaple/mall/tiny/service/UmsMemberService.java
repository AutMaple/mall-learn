package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 会员管理 Service
 */
public interface UmsMemberService {
    /**
     * @Author AutMaple
     * @Description 根据用户名获取会员
     * @Date 2022/7/23 11:57
     **/
    UmsMember getByUsername(String username);

    /**
     * @Author AutMaple
     * @Description 根据会员编号获取会员
     * @Date 2022/7/23 11:58
     **/
    UmsMember getById(Long id);

    /**
     * @Author AutMaple
     * @Description 会员注册
     * @Date 2022/7/23 11:58
     **/
    void register(String username, String password, String telephone, String authCode);

    /**
     * @Author AutMaple
     * @Description 生成验证码
     * @Date 2022/7/23 11:59
     **/
    String generateAutCode(String telephone);

    /**
     * @Author AutMaple
     * @Description 修改密码
     * @Date 2022/7/23 12:00
     **/
    void updatePassword(String telephone, String password, String authCode);

    /**
     * @Author AutMaple
     * @Description 获取当期登录会员
     * @Date 2022/7/23 12:00
     **/
    UmsMember getCurrentMember();

    /**
     * @Author AutMaple
     * @Description 根据会员 ID 修改会员积分
     * @Date 2022/7/23 12:01
     **/
    void updateIntegration(Long id, Integer integration);

    /**
     * @Author AutMaple
     * @Description 获取用户信息
     * @Date 2022/7/23 12:52
     **/
    UserDetails loadUserByUsername(String username);

    /**
     * @Author AutMaple
     * @Description 登录后获取 token
     * @Date 2022/7/23 12:02
     **/
    String login(String username, String password);

    /**
     * @Author AutMaple
     * @Description 刷新 Token
     * @Date 2022/7/23 12:03
     **/
    String refreshToken(String token);
}

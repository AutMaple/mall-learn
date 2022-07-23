package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.UmsMember;

/**
 * @ClassName UmsMemberCacheService
 * @Description 会员信息缓存业务
 * @Author AutMaple
 * @Date 2022/7/23 12:06
 * @Version 1.0
 **/
public interface UmsMemberCacheService {
    /**
     * @Author AutMaple
     * @Description 删除会员信息缓存
     * @Date 2022/7/23 12:07
     **/
    void delMember(Long memberId);
    
    /**
     * @Author AutMaple
     * @Description 根据用户名获取缓存中的信息
     * @Date 2022/7/23 12:07
     **/
    UmsMember getMember(String username);
    
    /**
     * @Author AutMaple
     * @Description 设置会员信息缓存
     * @Date 2022/7/23 12:08
     **/
    void setMember(UmsMember member);

    /**
     * @Author AutMaple
     * @Description 设置验证码
     * @Date 2022/7/23 12:08
     **/
    void setAuthCode(String telephone, String authCode);

    /**
     * @Author AutMaple
     * @Description 获取验证码
     * @Date 2022/7/23 12:09
     **/
    String getAutCOde(String telephone);
}

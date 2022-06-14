package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.common.utils.JwtTokenUtil;
import com.autmaple.mall.tiny.dao.UmsAdminRoleRelationDao;
import com.autmaple.mall.tiny.mbg.mapper.UmsAdminMapper;
import com.autmaple.mall.tiny.mbg.model.UmsAdmin;
import com.autmaple.mall.tiny.mbg.model.UmsAdminExample;
import com.autmaple.mall.tiny.mbg.model.UmsPermission;
import com.autmaple.mall.tiny.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Value("@{jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据用户名获取管理员信息
     * @param username 管理员用户名
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if(adminList != null && adminList.size() > 0)
            return adminList.get(0);
        return null;
    }

    /**
     * 实现管理员注册功能
     */
    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        UmsAdmin admin = new UmsAdmin();
        // 为什么要复制一份呢？
        BeanUtils.copyProperties(umsAdminParam, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(1);

        // 查询是否有相同的用户名
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if(adminList != null && adminList.size() > 0)
            return null;

        // 将密码进行加密操作,SpringSecurity 框架提供了密码加密的操作
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        adminMapper.insert(admin);
        return admin;
    }


    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password, userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e){
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }
}

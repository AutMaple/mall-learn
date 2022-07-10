package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.common.exception.Asserts;
import com.autmaple.mall.tiny.common.utils.JwtTokenUtil;
import com.autmaple.mall.tiny.common.utils.RequestUtil;
import com.autmaple.mall.tiny.common.utils.SpringUtil;
import com.autmaple.mall.tiny.dao.UmsAdminRoleRelationDao;
import com.autmaple.mall.tiny.dto.UpdateAdminPasswordParam;
import com.autmaple.mall.tiny.dto.userdetails.AdminUserDetails;
import com.autmaple.mall.tiny.mbg.mapper.UmsAdminLoginLogMapper;
import com.autmaple.mall.tiny.mbg.mapper.UmsAdminMapper;
import com.autmaple.mall.tiny.mbg.mapper.UmsAdminRoleRelationMapper;
import com.autmaple.mall.tiny.mbg.model.*;
import com.autmaple.mall.tiny.service.UmsAdminCacheService;
import com.autmaple.mall.tiny.service.UmsAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;

    @Value("@{jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据用户名获取管理员信息
     *
     * @param username 管理员用户名
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = getCacheService().getAdmin(username);
        // 如果在缓存中就直接返回
        if (admin != null) return admin;
        // 不再缓存中就从数据库进行读取
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.or().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(adminExample);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            // 将数据存入缓存中
            getCacheService().setAdmin(admin);
            return admin;
        }
        return null;
    }

    /**
     * 实现管理员注册功能
     */
    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        UmsAdmin admin = new UmsAdmin();
        // 将 UmsAdminParams 与 UmsAdmin 类型中类型相同的部分复制过去
        BeanUtils.copyProperties(umsAdminParam, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(1);

        // 查询是否有相同的用户名
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0)
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
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                Asserts.fail("密码不正确");
            }
            if (!userDetails.isEnabled())
                Asserts.fail("帐号已被禁用");

            // TODO 这两行代码的意思是啥？
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    /**
     * @Author AutMaple
     * @Description 插入用户登录日志到数据库中
     * @Date 2022/7/10 14:45
     **/
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin == null) return;
        UmsAdminLoginLog adminLoginLog = new UmsAdminLoginLog();
        adminLoginLog.setAdminId(admin.getId());
        adminLoginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        adminLoginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(adminLoginLog);
    }


    /**
     * @Author AutMaple
     * @Description 更新用户的登录时间
     * @Date 2022/7/10 14:51
     **/
    private void updateLoginTimeByUsername(String username) {
        UmsAdmin admin = new UmsAdmin();
        admin.setLoginTime(new Date());
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        // selective 表示将该行的某个字段进行更新，不加 selective 表示对改行的所有字段进行更新
        adminMapper.updateByExampleSelective(admin, example);
    }


    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        if (!StringUtil.isEmpty(keyword)) {
            example.or().andUsernameLike("%" + keyword + "%");
            example.or().andNickNameLike("%" + keyword + "%");
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        admin.setId(id);
        UmsAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
        if (rawAdmin.getPassword().equals(admin.getPassword())) {
            // 与原加密密码相同，无需更改
            admin.setPassword(null);
        } else {
            if (StringUtil.isEmpty(admin.getPassword())) {
                admin.setPassword(null);
            } else {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        int count = adminMapper.updateByPrimaryKeySelective(admin);
        getCacheService().deleteAdmin(id);
        return count;
    }

    @Override
    public int delete(Long id) {
        getCacheService().deleteAdmin(id);
        int count = adminMapper.deleteByPrimaryKey(id);
        getCacheService().deleteResourceList(id);
        return count;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(example);

        if (CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation relation = new UmsAdminRoleRelation();
                relation.setAdminId(adminId);
                relation.setRoleId(roleId);
                list.add(relation);
            }
            adminRoleRelationDao.insertList(list);
        }
        getCacheService().deleteResourceList(adminId);
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = getCacheService().getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList))
            return resourceList;
        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList))
            getCacheService().setResourceList(adminId, resourceList);
        return resourceList;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if (StrUtil.isEmpty(param.getUsername())
                || StrUtil.isEmpty(param.getOldPassword())
                || StrUtil.isEmpty(param.getOldPassword()))
            return -1;

        UmsAdminExample example = new UmsAdminExample();
        example.or().andUsernameEqualTo(param.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (CollUtil.isEmpty(adminList))
            return -2;
        UmsAdmin admin = adminList.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), admin.getPassword())) {
            return -3;
        }
        admin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        adminMapper.updateByPrimaryKeySelective(admin);
        getCacheService().deleteAdmin(admin.getId());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }
}

package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.common.exception.Asserts;
import com.autmaple.mall.tiny.common.utils.JwtTokenUtil;
import com.autmaple.mall.tiny.dto.userdetails.MemberDetails;
import com.autmaple.mall.tiny.mbg.mapper.UmsMemberLevelMapper;
import com.autmaple.mall.tiny.mbg.mapper.UmsMemberMapper;
import com.autmaple.mall.tiny.mbg.model.UmsMember;
import com.autmaple.mall.tiny.mbg.model.UmsMemberExample;
import com.autmaple.mall.tiny.mbg.model.UmsMemberLevel;
import com.autmaple.mall.tiny.mbg.model.UmsMemberLevelExample;
import com.autmaple.mall.tiny.service.UmsMemberCacheService;
import com.autmaple.mall.tiny.service.UmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UmsMemberMapper memberMapper;

    @Autowired
    private UmsMemberLevelMapper levelMapper;

    @Autowired
    private UmsMemberCacheService memberCacheService;

    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public UmsMember getByUsername(String username) {
        UmsMember member = memberCacheService.getMember(username);
        if (member != null) return member;
        UmsMemberExample example = new UmsMemberExample();
        example.or().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(memberList)) {
            member = memberList.get(0);
            memberCacheService.setMember(member);
        }
        return member;
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public void register(String username, String password, String telephone, String authCode) {
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }

        UmsMemberExample example = new UmsMemberExample();
        example.or().andUsernameEqualTo(username);
        example.or().andPhoneEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(memberList))
            Asserts.fail("用户已经存在");

        UmsMember member = new UmsMember();
        member.setUsername(username);
        member.setPhone(telephone);
        member.setPassword(passwordEncoder.encode(password));
        member.setCreateTime(new Date());
        member.setStatus(1);
        UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
        levelExample.or().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> memberLevelList = levelMapper.selectByExample(levelExample);
        if (CollUtil.isNotEmpty(memberLevelList))
            member.setMemberLevelId(memberLevelList.get(0).getId());

        memberMapper.insert(member);
        member.setPassword(null);
    }

    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StrUtil.isEmpty(authCode))
            return false;
        String realAuthCode = memberCacheService.getAutCOde(telephone);
        return realAuthCode.equals(authCode);
    }

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++)
            sb.append(random.nextInt(10));
        memberCacheService.setAuthCode(telephone, sb.toString());
        return sb.toString();
    }

    @Override
    public void updatePassword(String telephone, String password, String authCode) {
        UmsMemberExample example = new UmsMemberExample();
        example.or().andPhoneEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollUtil.isEmpty(memberList))
            Asserts.fail("帐号不存在");
        if (!verifyAuthCode(authCode, telephone))
            Asserts.fail("验证码错误");
        UmsMember member = memberList.get(0);
        member.setPassword(passwordEncoder.encode(password));
        memberMapper.updateByPrimaryKeySelective(member);
        memberCacheService.delMember(member.getId());
    }

    @Override
    public UmsMember getCurrentMember() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        return memberDetails.getMember();
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
        memberCacheService.delMember(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if (member != null)
            return new MemberDetails(member);
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        String token = null;
        try {
            if (!passwordEncoder.matches(password, userDetails.getPassword()))
                throw new BadCredentialsException("密码不正确");
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常: {}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshToken(token);
    }
}

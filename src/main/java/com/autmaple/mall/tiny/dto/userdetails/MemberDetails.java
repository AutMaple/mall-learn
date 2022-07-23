package com.autmaple.mall.tiny.dto.userdetails;

import com.autmaple.mall.tiny.mbg.model.UmsMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @ClassName MemberDetails
 * @Description 会员详细信息
 * @Author AutMaple
 * @Date 2022/7/23 12:45
 * @Version 1.0
 **/
public class MemberDetails implements UserDetails {
    private final UmsMember member;

    public MemberDetails(UmsMember member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("TEST"));

    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return member.getStatus() == 1;
    }

    public UmsMember getMember() {
        return member;
    }
}

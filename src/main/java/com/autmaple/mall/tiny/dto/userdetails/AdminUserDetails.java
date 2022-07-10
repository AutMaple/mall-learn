package com.autmaple.mall.tiny.dto.userdetails;

import com.autmaple.mall.tiny.mbg.model.UmsAdmin;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName AdminUserDetails
 * @Description SpringSecurity 需要用户的详细信息
 * @Author AutMaple
 * @Date 2022/6/16 21:24
 * @Version 1.0
 **/
public class AdminUserDetails implements UserDetails {
    private final UmsAdmin umsAdmin;

    private final List<UmsResource> resourceList;

    public AdminUserDetails(UmsAdmin admin, List<UmsResource> resourceList) {
        this.umsAdmin = admin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resourceList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
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
        return umsAdmin.getStatus().equals(1);
    }
}

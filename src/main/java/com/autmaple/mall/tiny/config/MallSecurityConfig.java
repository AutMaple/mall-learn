package com.autmaple.mall.tiny.config;

import com.autmaple.mall.tiny.component.DynamicSecurityMetaDataSource;
import com.autmaple.mall.tiny.mbg.model.UmsResource;
import com.autmaple.mall.tiny.service.DynamicSecurityService;
import com.autmaple.mall.tiny.service.UmsAdminService;
import com.autmaple.mall.tiny.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MallSecurityConfig
 * @Description 安全相关的配置类
 * @Author AutMaple
 * @Date 2022/7/20 21:50
 * @Version 1.0
 **/
@Configuration
public class MallSecurityConfig {
    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsResourceService resourceService;

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetaDataSource dynamicSecurityMetaDataSource() {
        return new DynamicSecurityMetaDataSource();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> adminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
            List<UmsResource> resourceList = resourceService.listAll();
            for (UmsResource resource : resourceList) {
                map.put(resource.getUrl(), new SecurityConfig(resource.getId() + ":" + resource.getName()));
            }
            return map;
        };
    }
}

package com.autmaple.mall.tiny.service;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @ClassName DynamicSecurityService
 * @Description
 * @Author AutMaple
 * @Date 2022/7/20 21:47
 * @Version 1.0
 **/
public interface DynamicSecurityService {
    
    /**
     * @Author AutMaple
     * @Description 加载资源 ANT 通配符和资源对应 MAP
     * @Date 2022/7/20 21:49
     **/
    Map<String, ConfigAttribute> loadDataSource();
}

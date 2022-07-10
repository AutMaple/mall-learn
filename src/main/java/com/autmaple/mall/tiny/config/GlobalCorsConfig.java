package com.autmaple.mall.tiny.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName GlobalCorsConfig
 * @Description 跨域资源共享配置类，用于前后端分离的项目
 * @Author AutMaple
 * @Date 2022/7/9 23:28
 * @Version 1.0
 **/
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有域名进行跨域调用
        corsConfiguration.addAllowedOrigin("*");
        // 允许跨域发送 Cookie
        corsConfiguration.setAllowCredentials(true);
        // 放行全部的原始头信息
        corsConfiguration.addAllowedHeader("*");
        // 允许所有方法进行跨域调用
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}

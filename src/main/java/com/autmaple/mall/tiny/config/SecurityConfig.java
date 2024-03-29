package com.autmaple.mall.tiny.config;

import com.autmaple.mall.tiny.component.JwtAuthenticationTokenFilter;
import com.autmaple.mall.tiny.component.RestAuthenticationEntryPoint;
import com.autmaple.mall.tiny.component.RestfulAccessDeniedHandler;
import com.autmaple.mall.tiny.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * SpringSecurity 的配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private UmsAdminService adminService;

    @Autowired
    public SecurityConfig(RestfulAccessDeniedHandler restfulAccessDeniedHandler,
                          RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restfulAccessDeniedHandler = restfulAccessDeniedHandler;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf() // 由于使用的是 JWT， 我们不需要 CSRF
                .disable()
                .sessionManagement() // 基于 token, 所以不需要 session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                .antMatchers("/admin/login", "/admin/register") // 登录注册应该允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS) // 跨域请求首先发起一个 Options 请求
                .permitAll()
                .antMatchers("/**") // 测试时，所有资源均允许访问
                .permitAll()
                .anyRequest() // 除了上面的请求外，其他的请求都需要进行鉴权认证
                .authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加 JWT 过滤器
        httpSecurity.addFilterBefore(getJwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // 添加自定义未授权和未登录请求的返回结果
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter getJwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        // 获取用户信息和用户的权限信息
//        return username -> adminService.loadUserByUsername(username);
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

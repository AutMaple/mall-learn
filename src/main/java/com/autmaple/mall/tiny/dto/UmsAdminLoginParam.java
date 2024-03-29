package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName UmsAdminLoginParam
 * @Description 管理员登录参数
 * @Author AutMaple
 * @Date 2022/6/15 21:23
 * @Version 1.0
 **/
public class UmsAdminLoginParam {
    @Schema(description="用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @Schema(description="密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName UpdateAdminPasswordParam
 * @Description
 * @Author AutMaple
 * @Date 2022/7/10 12:04
 * @Version 1.0
 **/
public class UpdateAdminPasswordParam {
    @NotEmpty(message = "用户名不能为空")
    @Schema(description="用户名", required = true)
    private String username;

    @NotEmpty(message = "旧密码不能为空")
    @Schema(description="旧密码", required = true)
    private String oldPassword;

    @NotEmpty(message = "新密码不能为空")
    @Schema(description="新密码", required = true)
    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

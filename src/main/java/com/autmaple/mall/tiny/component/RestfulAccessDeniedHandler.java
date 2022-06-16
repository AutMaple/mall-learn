package com.autmaple.mall.tiny.component;

import cn.hutool.json.JSONUtil;
import com.autmaple.mall.tiny.common.api.CommonResult;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName RestfulAccessDeniedHandler
 * Description 当接口没有访问权限时，返回自定义的结果
 *
 * @Author AutMaple
 * @Date 2022/6/16 19:51
 * Version 1.0
 **/
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("applicatin/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}

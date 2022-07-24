package com.autmaple.mall.tiny.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SpringDocConfig
 * @Description SpringDoc 的配置类
 * @Author AutMaple
 * @Date 2022/7/24 12:33
 * @Version 1.0
 **/
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info());
    }

    public Info info(){
        Info info = new Info();
        info.title("Mall-Learn 项目接口文档")
                .description("电商项目文档")
                .version("1.0.0");
        return info;
    }
}

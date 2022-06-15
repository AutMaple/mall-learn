package com.autmaple.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.autmaple.mall.tiny.mbg.mapper", "com.autmaple.mall.tiny.dao"})
public class MybatisConfig {

}

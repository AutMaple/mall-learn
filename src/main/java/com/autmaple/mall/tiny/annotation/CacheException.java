package com.autmaple.mall.tiny.annotation;


import java.lang.annotation.*;

/**
 * @Author AutMaple
 * @Description 自定义注解，有这个注解缓存方法会抛出异常
 * @Date 2022/7/23 12:22
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}

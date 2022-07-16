package com.autmaple.mall.tiny.validator;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author AutMaple
 * @Description 用于验证状态是否在指定范围内的注解
 * @Date 2022/7/16 10:35
 **/
@Document
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = FlagValidatorClass.class)
public @interface FlagValidator {

    String[] value() default {};

    String message() default "Flag is not found!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.autmaple.mall.tiny.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName FlagValidatorClass
 * @Description 状态的约束校验器
 * @Author AutMaple
 * @Date 2022/7/16 10:27
 * @Version 1.0
 **/
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {
    private String[] values;

    @Override
    public void initialize(FlagValidator flagValidator) {
        this.values = flagValidator.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = false;
        if (value == null)
            return true;
        for (String s : values) {
            if (s.equals(String.valueOf(value))) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}

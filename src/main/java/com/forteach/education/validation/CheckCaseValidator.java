package com.forteach.education.validation;

import com.forteach.education.common.keyword.CaseModeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 10:12
 * @Version: 1.0
 * @Description: 校验字母大小写
 */
public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseModeEnum caseModeEnum;


    @Override
    public void initialize(CheckCase constraintAnnotation) {
        this.caseModeEnum = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (caseModeEnum == CaseModeEnum.UPPER) {
            return value.equals(value.toUpperCase());
        } else {
            return value.equals(value.toLowerCase());
        }
    }
}

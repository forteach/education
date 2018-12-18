package com.forteach.education.validation;

import com.forteach.education.common.keyword.SortVoEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 09:01
 * @Version: 1.0
 * @Description:
 */
public class SortVoValidation implements ConstraintValidator<SortVo, String> {

    private SortVoEnum sortVoEnum;

    @Override
    public void initialize(SortVo constraintAnnotation) {
        this.sortVoEnum = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null){
            return false;
        }
        if (sortVoEnum == SortVoEnum.ASC || sortVoEnum == SortVoEnum.DESC){
            return true;
        }else {
            return false;
        }
    }
}

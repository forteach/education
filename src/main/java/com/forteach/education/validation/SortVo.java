package com.forteach.education.validation;

import com.forteach.education.common.SortVoEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 10:18
 * @Version: 1.0
 * @Description: 校验分页排序
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = SortVoValidation.class)
@Documented
public @interface SortVo {

    String message() default "查询类型不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    SortVoEnum value();
}

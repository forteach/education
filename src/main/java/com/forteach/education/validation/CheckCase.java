package com.forteach.education.validation;

import com.forteach.education.common.keyword.CaseModeEnum;

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
 * @Date: 18-11-21 09:38
 * @Version: 1.0
 * @Description:
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)
@Documented
public @interface CheckCase {

    String message() default "查询类型不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CaseModeEnum value();
}

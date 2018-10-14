package com.n26.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, PARAMETER})
@Constraint(validatedBy = NotFutureOrCurrentDateValidator.class)
public @interface NotFutureOrCurrentDate {
    String message() default "{com.n26.validator.NotFutureOrCurrentDate.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

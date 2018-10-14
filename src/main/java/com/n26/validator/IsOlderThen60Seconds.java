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
@Constraint(validatedBy = IsOlderThen60SecondsValidator.class)
public @interface IsOlderThen60Seconds {

    String message() default "{com.n26.validator.IsOlderThen60Seconds.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

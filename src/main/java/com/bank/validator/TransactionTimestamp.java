package com.bank.validator;

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
@Constraint(validatedBy = TransactionTimestampValidator.class)
public @interface TransactionTimestamp {

    String message() default "{com.n26.validator.TransactionTimestamp.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

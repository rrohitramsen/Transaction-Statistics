package com.n26.validator;

import com.n26.exception.FutureTransactionException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class NotFutureOrCurrentDateValidator implements ConstraintValidator<NotFutureOrCurrentDate, Date> {

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {

        Date currentTime = new Date();
        boolean result = value.getTime() < currentTime.getTime();

        if (result == false) {
            throw new FutureTransactionException("Time should not be current or future");
        }else {
            return result;
        }
    }
}
